package com.tat.thai_herb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.RecyclerViewCallBack

class HomeAdapter(var context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var adapter: ItemRecyclerViewAdapterHome

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recyclerview_main, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapter = ItemRecyclerViewAdapterHome(context)
        holder.itemRecyclerViewHome.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL!!,false)
        holder.itemRecyclerViewHome.adapter = adapter

        holder.herderClickItem.setOnClickListener {

        }

        adapter.setOnSelectItemViewListener(object : RecyclerViewCallBack {
            override fun onClickItem(position: Int) {

            }
        })
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemRecyclerViewHome: RecyclerView = view.findViewById(R.id.itemRecyclerViewHome)
        var herderClickItem: LinearLayout = view.findViewById(R.id.herderClickItem)
        var textShoweTitwlItem: TextView = view.findViewById(R.id.textShoweTitwlItem)
    }
}