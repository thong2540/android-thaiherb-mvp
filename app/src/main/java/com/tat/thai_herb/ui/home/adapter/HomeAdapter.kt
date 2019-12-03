package com.tat.thai_herb.ui.home.adapter

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
import com.tat.thai_herb.model.respone.DataList
import com.tat.thai_herb.model.respone.SymptomList


class HomeAdapter(var context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var listener: RecyclerViewCallBack
    var itemList: List<DataList> = arrayListOf()

    fun setOnDataRecyclerViewListener(listener: RecyclerViewCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recyclerview_main, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = itemList[position]
        var itemAll = itemList[position].system[0].symptom_list

        holder.textShowTitlelItem!!.text = item.title
        holder.herderClickItem!!.setOnClickListener {
            listener.onClickItem(position)
        }

        holder.adapter.setData(itemAll)

        holder.adapter.setOnSelectItemViewListener(object :RecyclerViewCallBack{

            override fun onClickItem(position: Int) {
                listener.onPresentData(itemAll[position])
            }

            override fun onPresentData(data: SymptomList) {

            }

        })
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemRecyclerViewHome: RecyclerView? = null
        var herderClickItem: LinearLayout? = null
        var textShowTitlelItem: TextView? = null
        var adapter: ItemRecyclerViewAdapterHome

        init {
            itemRecyclerViewHome = view.findViewById(R.id.itemRecyclerViewHome)
            herderClickItem = view.findViewById(R.id.herderClickItem)
            textShowTitlelItem = view.findViewById(R.id.textShoweTitwlItem)

            //setRecyclerView
            adapter = ItemRecyclerViewAdapterHome(view.context)
            itemRecyclerViewHome!!.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            itemRecyclerViewHome!!.adapter = adapter
        }
    }
}