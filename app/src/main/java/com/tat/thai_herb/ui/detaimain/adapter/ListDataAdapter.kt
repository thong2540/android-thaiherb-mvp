package com.tat.thai_herb.ui.detaimain.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.ActionClickHeader
import com.tat.thai_herb.model.respone.Detail

class ListDataAdapter : RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    var mContext: Context? = null
    var mData: List<Detail> = arrayListOf()

    private var mCallBack: ActionClickHeader? = null

    fun setOnClickResponseCallBack(responseCallBack: ActionClickHeader?) {
        this.mCallBack = responseCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_herbs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detil: Detail = mData[position]
        holder.textHerb.text = detil.title
        Glide.with(mContext!!).load(detil.image).into(holder.imgHerb)
        holder.itemView.setOnClickListener {
            if (mCallBack != null) mCallBack!!.onClickItem(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgHerb: ImageView = itemView.findViewById<View>(R.id.imgHerb) as ImageView
        val textHerb: TextView = itemView.findViewById<View>(R.id.textHerb) as TextView
    }
}
