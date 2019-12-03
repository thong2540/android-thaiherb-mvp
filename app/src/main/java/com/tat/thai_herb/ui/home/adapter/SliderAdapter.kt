package com.tat.thai_herb.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.tat.thai_herb.R

class SliderAdapter(private val context: Context) : SliderViewAdapter<SliderAdapter.ViewHolder>() {

    var mCount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FKanphlu.jpg?alt=media&token=aaadf2c3-e0eb-4fb9-9312-1c8ab2237aa9")
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
            1 -> {
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FKhun.jpg?alt=media&token=d8e055cc-ca66-4299-8fba-843b18430f62")
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
            2 -> {
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FThianBan.jpg?alt=media&token=b4a7d3ad-aa66-4c2f-b80d-fa9eed78ded3")
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
            3 -> {
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FDipli.png?alt=media&token=66e227d4-101e-4d01-95d8-19519a5821c7")
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
            4 -> {
                viewHolder.imageGifContainer.visibility = View.GONE
                Glide.with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/thaiherb-b276f.appspot.com/o/PictureHerbs%2FMakhamPom.jpg?alt=media&token=b088e9ae-dd53-4ec2-bdc9-f7af3a17cb38")
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
            }
        }
    }

    override fun getCount(): Int {
        return mCount
    }

    inner class ViewHolder(view: View) : SliderViewAdapter.ViewHolder(view) {
        val imageViewBackground: ImageView = view.findViewById(R.id.iv_auto_image_slider)
        val imageGifContainer: ImageView = view.findViewById(R.id.iv_gif_container)

    }
}