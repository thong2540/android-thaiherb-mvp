package com.tat.thai_herb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.thai_herb.R

class ItemRecycleviewAdapterHome(var context: Context) : RecyclerView.Adapter<ItemRecycleviewAdapterHome.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recyclerview_herbs, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }
}