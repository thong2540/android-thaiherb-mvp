package com.tat.thai_herb.ui.gallery.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.RecyclerViewCallBack
import com.tat.thai_herb.model.respone.ContentImage


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    var itemList: List<ContentImage> = arrayListOf()
    private lateinit var listener: RecyclerViewCallBack

    fun setOnDataRecyclerViewListener(listener: RecyclerViewCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        val width: Int = Resources.getSystem().displayMetrics.widthPixels

        Glide
            .with(holder.itemView.context)
            .load(item.part)
            .override(width/3,width/3)
            .into(holder.imgAllHerb)

        holder.itemView.setOnClickListener {
            listener.onClickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgAllHerb: ImageView = view.findViewById(R.id.imgAllHerb)
    }
}