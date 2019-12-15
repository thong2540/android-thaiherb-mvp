package com.tat.thai_herb.ui.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.hideStatusBar
import com.tat.thai_herb.listener.SearchViewCallBack
import com.tat.thai_herb.model.respone.DataGame
import com.tat.thai_herb.model.respone.SearchList
import com.tat.thai_herb.ui.game.adapter.JigsawAdapter
import com.tat.thai_herb.ui.game.presenter.GamePresenter
import com.tat.thai_herb.utilty.game.puzzle.PuzzleActivity
import kotlinx.android.synthetic.main.activity_jigsaw.*

class JigsawActivity : AppCompatActivity(), GameView.View {

    private lateinit var adapter: JigsawAdapter
    private var presenter: GamePresenter? = null
    private var itemList: List<DataGame> = arrayListOf()
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jigsaw)
        this.hideStatusBar()
        adapter = JigsawAdapter()
        presenter = GamePresenter(this)
        presenter!!.mekeData(this@JigsawActivity)

        setUpView()

        onEvent()
    }

    private fun setUpView() {
        recyclerViewGame1.setHasFixedSize(true)
        recyclerViewGame1.layoutManager = GridLayoutManager(this, 2)
        recyclerViewGame1.adapter = adapter

    }

    private fun onEvent() {
        game1_back.setOnClickListener {
            finish()
        }

        adapter.setOnClickResponseCallBack(object : SearchViewCallBack{
            override fun onClickItem(position: Int) {
                gorp.visibility = View.VISIBLE
                go_on.visibility = View.VISIBLE
                index = position
                title_game1.text = itemList[position].title
                sys_game1.text = itemList[position].symptom
                des_game1.text = itemList[position].description
            }

        })

        go_on.setOnClickListener {
            val intent = Intent(this,PuzzleActivity::class.java)
            intent.putExtra("part",itemList[index].part)
            startActivity(intent)
        }
    }

//    override fun respone(list: List<SearchList>) {
//        itemList = list
//        adapter.itemList = list
//        adapter.notifyDataSetChanged()
//    }

    override fun respone(list: List<DataGame>) {
        itemList = list
        adapter.itemList = list
        adapter!!.notifyDataSetChanged()
    }
}
