package com.tat.thai_herb.ui.game.adapter

import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.SearchViewCallBack
import com.tat.thai_herb.model.respone.DataGame
import com.tat.thai_herb.model.respone.SearchList
import com.tat.thai_herb.utilty.game.puzzle.PuzzleActivity

class JigsawAdapter : RecyclerView.Adapter<JigsawAdapter.ViewHolder>() {

    var itemList: List<DataGame> = arrayListOf()

    private var mCallBack: SearchViewCallBack? = null

    fun setOnClickResponseCallBack(responseCallBack: SearchViewCallBack?) {
        this.mCallBack = responseCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        val width: Int = Resources.getSystem().displayMetrics.widthPixels / 4

        Glide
            .with(holder.itemView.context)
            .load(item.image)
            .override(width, width)
            .into(holder.imgAllHerb)

        holder.itemView.setOnClickListener {
            mCallBack!!.onClickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgAllHerb: ImageView = view.findViewById(R.id.imgAllHerb)
    }
}