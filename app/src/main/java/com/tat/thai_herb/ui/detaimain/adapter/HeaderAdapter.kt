package com.tat.thai_herb.ui.detaimain.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.ActionClickHeader

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {

    var mContext: Context? = null
    var listHeader: List<String> = arrayListOf()
    private var lastClickedPosition = 0
    private var mClickHeader: ActionClickHeader? = null

    fun setOnClickHeader(clickHeader: ActionClickHeader?) {
        mClickHeader = clickHeader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textheader.text = listHeader[position]
        holder.textheader.setOnClickListener {
            if (mClickHeader != null) mClickHeader!!.onClickList(listHeader[position])
            lastClickedPosition = position
            notifyDataSetChanged()
        }
        if (lastClickedPosition == position) {
            holder.textheader.background = mContext!!.resources.getDrawable(R.drawable.bg_item_header)
            holder.textheader.setTextColor(mContext!!.resources.getColor(R.color.colorWhite))
        } else {
            holder.textheader.background = mContext!!.resources.getDrawable(R.drawable.bg_edittext)
            holder.textheader.setTextColor(mContext!!.resources.getColor(R.color.greenDark))
        }
    }

    override fun getItemCount(): Int {
        return listHeader.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textheader: TextView = itemView.findViewById(R.id.textheader)

    }

}
