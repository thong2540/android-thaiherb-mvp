package com.tat.thai_herb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.RecyclerViewCallBack

class ItemRecyclerViewAdapterHome(var context: Context) : RecyclerView.Adapter<ItemRecyclerViewAdapterHome.ViewHolder>() {

    private lateinit var listener: RecyclerViewCallBack

    fun setOnSelectItemViewListener(listener: RecyclerViewCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recyclerview_herbs, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            this.listener.onClickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgHerb: ImageView = view.findViewById(R.id.imgHerb)
        var textHerb: TextView = view.findViewById(R.id.textHerb)
        var textClick: TextView = view.findViewById(R.id.textClick)
    }
}