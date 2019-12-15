package com.tat.thai_herb.ui.game


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tat.thai_herb.R
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        onEvent(view)

        return view
    }

    private fun onEvent(view: View?) {
        view!!.game1.setOnClickListener {
            val intent = Intent(context!!,JigsawActivity::class.java)
            startActivity(intent)
        }

        view!!.game2.setOnClickListener {
            try {
                val intent: Intent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.DefaultCompany.MemoryHerbGame")
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                startActivity(intent)
            } catch (e: NullPointerException) {
                Toast.makeText(context, "ไม่สามารถเปิดเกม ได้", Toast.LENGTH_SHORT).show()
            }
        }

        view!!.game3.setOnClickListener {
            try {
                val intent: Intent =
                    activity!!.packageManager.getLaunchIntentForPackage("com.Wongrawee.HerbGame")
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                startActivity(intent)
            } catch (e: NullPointerException) {
                Toast.makeText(context, "ไม่สามารถเปิดเกม ได้", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
