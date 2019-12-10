package com.tat.thai_herb.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.SearchViewCallBack
import com.tat.thai_herb.model.respone.SearchList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var mData: List<SearchList> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var mCallBack: SearchViewCallBack? = null

    fun setOnClickResponseCallBack(responseCallBack: SearchViewCallBack?) {
        this.mCallBack = responseCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: SearchList = mData[position]
        holder.title_item_serach.text = data.title
        holder.des_item_serach.text = data.symptom
        Glide.with(holder.itemView.context).load(data.image).into(holder.image_item_serach)
        holder.itemView.setOnClickListener {
            if (mCallBack != null) mCallBack!!.onClickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image_item_serach: ImageView =
            itemView.findViewById<View>(R.id.image_item_serach) as ImageView
        val title_item_serach: TextView =
            itemView.findViewById<View>(R.id.title_item_serach) as TextView
        val des_item_serach: TextView =
            itemView.findViewById<View>(R.id.des_item_serach) as TextView
    }
}