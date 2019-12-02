package com.tat.thai_herb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tat.thai_herb.listener.RecyclerViewCallBack
import com.tat.thai_herb.model.respone.DataList


class HomeAdapter(var context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var adapter: ItemRecyclerViewAdapterHome
    var itemList: List<DataList> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recyclerview_main, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = itemList[position]
        var itemAll = itemList[position].system[1].symptom_list

        holder.textShoweTitwlItem.text = item.key

        adapter = ItemRecyclerViewAdapterHome(context)
        holder.itemRecyclerViewHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.itemList = itemAll
        holder.itemRecyclerViewHome.adapter = adapter
        adapter.notifyDataSetChanged()

        holder.herderClickItem.setOnClickListener {

        }

        adapter.setOnSelectItemViewListener(object : RecyclerViewCallBack {
            override fun onClickItem(position: Int) {

            }
        })
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemRecyclerViewHome: RecyclerView = view.findViewById(R.id.itemRecyclerViewHome)
        var herderClickItem: LinearLayout = view.findViewById(R.id.herderClickItem)
        var textShoweTitwlItem: TextView = view.findViewById(R.id.textShoweTitwlItem)
    }
}