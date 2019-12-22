package com.tat.thai_herb.utilty.game.puzzle;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tat.thai_herb.R;
import com.tat.thai_herb.utilty.game.adapter.PuzzleAdapter;
import com.tat.thai_herb.utilty.game.listener.GameListener;
import com.tat.thai_herb.utilty.game.models.Pieces;
import com.tat.thai_herb.utilty.game.models.PuzzlePiece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PuzzleActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    FrameLayout scrollView;
    ImageView imageView,image_show_title;
    TextView text_time_jic;
    Context context;
    List<Pieces> piecesModelListMain = new ArrayList<Pieces>();
    HashMap<String, Pieces> piecesModelHashMap = new HashMap<String, Pieces>();
    int countGrid = 0;
    ArrayList<PuzzlePiece> puzzlePiecesList = new ArrayList<PuzzlePiece>();
    private RecyclerView rvPuzzle;
    private RecyclerView.LayoutManager linearLayoutManager;
    private PuzzleAdapter puzzleListAdapter;
    Puzzle puzzle;

    boolean widthCheck = true;
    int widthFinal;
    int heightFinal;
    private int part;

    private CountDownTimer timer;
//    private GameListener gameListener;
    private long countTime;
    private int scroe;

    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private int nScroe;

//    public void setGameListener(GameListener listener){
//        this.gameListener = listener;
//    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_activity);
        hideStatusBar();
        if (getIntent() == null) return;
        Intent intent = getIntent();
        part = intent.getIntExtra("part", 0);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("NewUser").child(firebaseUser.getUid()).child("scoreFirst");


        context = this;
        imageView = (ImageView) findViewById(R.id.frameImage);
        image_show_title = (ImageView) findViewById(R.id.image_show_title);

        //Time
        text_time_jic = (TextView) findViewById(R.id.text_time_jic);

        scrollView = (FrameLayout) findViewById(R.id.scrollView);
        scrollView.setOnDragListener(new MyDragListener(null));
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setOnDragListener(new MyDragListener(null));
        rvPuzzle = (RecyclerView) findViewById(R.id.listView2);
        rvPuzzle.setOnDragListener(new MyDragListener(null));
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvPuzzle.setLayoutManager(linearLayoutManager);
        puzzle = new Puzzle();
        puzzlePiecesList.clear();

        final ViewTreeObserver obs = scrollView.getViewTreeObserver();
        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {//width=lGrid.getWidth();
                if (widthCheck) {
                    widthFinal = scrollView.getWidth();
                    heightFinal = scrollView.getHeight();
                    puzzlePiecesList = puzzle.createPuzzlePieces(
                            PuzzleActivity.this,
                            null,
                            widthFinal,
                            heightFinal,
                            image_show_title,
                            "/puzzles/",
                            3,
                            3,
                            part);
                    getAdapter();
                    widthCheck = false;
                }
            }
        });

        setPuzzleListAdapter();
        setTime();
        getData();
    }

    private void getData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nScroe = dataSnapshot.getValue(int.class);
                System.out.println("SCORE " + nScroe);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void upData(int score){
        int integer;
        integer = (nScroe + score);
        databaseReference.setValue(integer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },2000);
                }
            }
        });

    }

    private void setTime() {
        timer = new CountDownTimer(55000, 1000) {
            public void onTick(long millisUntilFinished) {
                countTime = millisUntilFinished /1000;
                text_time_jic.setText("" +countTime);
            }
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "หมดเวลาแล้วจ้าาา !!",
                        Toast.LENGTH_LONG).show();
                upData(10);
            }
        };
        timer.start();
    }

    void hideStatusBar() {
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void getAdapter() {
        RelativeLayout.LayoutParams params;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                PuzzlePiece piece = puzzlePiecesList.get(countGrid);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                int dimX = piece.getAnchorPoint().x - piece.getCenterPoint().x;
                int dimY = piece.getAnchorPoint().y - piece.getCenterPoint().y;

                params.setMargins(dimX, dimY, 0, 0);
                final ImageView button2 = new ImageView(this);
                button2.setId(generateViewId());
                button2.setTag(i + "," + j);

                button2.setImageResource(R.drawable.ic_1);

                button2.setOnDragListener(new MyDragListener(button2));
                button2.setLayoutParams(params);
                relativeLayout.addView(button2);

                Pieces piecesModel = new Pieces();
                piecesModel.setpX(i);
                piecesModel.setpY(j);
                piecesModel.setPosition(countGrid);
                piecesModel.setOriginalResource(puzzlePiecesList.get(countGrid).getImage());
                piecesModelListMain.add(piecesModel);
                Collections.shuffle(piecesModelListMain);
                piecesModelHashMap.put(i + "," + j, piecesModel);
                piecesModel = null;

                countGrid++;

            }
        }
    }


    public int generateViewId() {
        final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public void setPuzzleListAdapter() {
        if (puzzleListAdapter != null)
            puzzleListAdapter = null;

        puzzleListAdapter = new PuzzleAdapter(this, piecesModelListMain);
        rvPuzzle.setHasFixedSize(true);
        rvPuzzle.setAdapter(puzzleListAdapter);

        puzzleListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    static public class MyClickListener implements View.OnLongClickListener {

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub

            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            view.startDrag(data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );

            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    public class MyDragListener implements View.OnDragListener {

        final ImageView imageView;

        public MyDragListener(final ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundResource(R.drawable.target_shape);    //change the shape of the view
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundResource(R.drawable.normal_shape);    //change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    //v is the dynamic grid imageView, we accept the drag item
                    //view is listView imageView the dragged item
                    if (v == imageView) {
                        View view = (View) event.getLocalState();

                        ViewGroup owner = (ViewGroup) v.getParent();
                        if (owner == relativeLayout) {
                            String selectedViewTag = view.getTag().toString();

                            Pieces piecesModel = piecesModelHashMap.get(v.getTag().toString());
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();

                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) v;
                                imageView.setImageBitmap(piecesModel.getOriginalResource());
                                piecesModelListMain.remove(piecesModel);
                                setPuzzleListAdapter();
                                piecesModel = null;
                                if (piecesModelListMain.size() == 0) {
                                    timer.cancel();
                                    if (countTime <= 50 && countTime > 40){
                                        scroe = 100;
                                    }else if (countTime <= 40 && countTime > 30) {
                                        scroe = 80;
                                    }else if (countTime <= 30 && countTime > 20) {
                                        scroe = 50;
                                    }else if (countTime <= 20 && countTime > 10) {
                                        scroe = 35;
                                    }else if (countTime <= 10 && countTime > 0) {
                                        scroe = 20;
                                    }

                                    Toast.makeText(getApplicationContext(), "SUCCESS !!", Toast.LENGTH_SHORT).show();
                                    upData(scroe);
                                } else {
                                    Toast.makeText(getApplicationContext(), "The correct Puzzle", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                piecesModel = null;
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Not the correct Puzzle", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            View view1 = (View) event.getLocalState();
                            view1.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "You can't drop the image here", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    } else if (v == scrollView) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "You can't drop the image here", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (v == rvPuzzle) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "You can't drop the image here", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "You can't drop the image here", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    break;
                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundResource(R.drawable.normal_shape);	//go back to normal shape
                default:
                    break;
            }
            return true;
        }
    }
}
