package com.tat.thai_herb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tat.thai_herb.listener.RecyclerViewCallBack
import com.tat.thai_herb.model.respone.SymptomList


class ItemRecyclerViewAdapterHome(var context: Context) : RecyclerView.Adapter<ItemRecyclerViewAdapterHome.ViewHolder>() {

    private lateinit var listener: RecyclerViewCallBack
    private var itemList: List<SymptomList> = arrayListOf()

    fun setData(data: List<SymptomList>) {
        if (itemList !== data) {
            itemList = data
            notifyDataSetChanged()
        }
    }

    fun setOnSelectItemViewListener(listener: RecyclerViewCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(com.tat.thai_herb.R.layout.item_recyclerview_herbs, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        Glide.with(context).load(item.image).into(holder.imgHerb)
        holder.textHerb.text = item.title

        holder.itemView.setOnClickListener {
            this.listener.onClickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgHerb: ImageView = view.findViewById(com.tat.thai_herb.R.id.imgHerb)
        var textHerb: TextView = view.findViewById(com.tat.thai_herb.R.id.textHerb)
    }
}