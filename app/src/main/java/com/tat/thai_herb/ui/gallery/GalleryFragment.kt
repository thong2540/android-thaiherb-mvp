package com.tat.thai_herb.ui.gallery


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.logout
import com.tat.thai_herb.listener.RecyclerViewCallBack
import com.tat.thai_herb.model.respone.ContentImage
import com.tat.thai_herb.model.respone.SymptomList
import com.tat.thai_herb.ui.gallery.adapter.GalleryAdapter
import com.tat.thai_herb.ui.gallery.presenter.GalleryPresenter
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.android.synthetic.main.header_toolbar_main.view.*

class GalleryFragment : Fragment(),GalleryView.View {

    private lateinit var presenter: GalleryPresenter
    private lateinit var adapter: GalleryAdapter
    var dataList: List<ContentImage> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        presenter = GalleryPresenter(this)
        presenter.getListImage()

        setupRecyclerView(view)

        onEvent(view)

        return view
    }

    private fun setupRecyclerView(view: View?) {
        view!!.heard_gallery.textViewMain.text = "แกลลอรี่"

        adapter = GalleryAdapter()
        view.mainRecyclerGallery.setHasFixedSize(true)
        view.mainRecyclerGallery.layoutManager = GridLayoutManager(context, 3)
        view.mainRecyclerGallery.adapter = adapter


    }

    private fun onEvent(view: View?) {
        view!!.heard_gallery.logoutMain.setOnClickListener {
            this.logout()
        }

        adapter.setOnDataRecyclerViewListener(object : RecyclerViewCallBack {
            override fun onClickItem(position: Int) {

            }

            override fun onPresentData(data: SymptomList) {

            }
        })
    }

    override fun responeImage(part: List<ContentImage>) {
        dataList = part
        adapter.itemList = dataList
        adapter.notifyDataSetChanged()
    }

    override fun showeLoding() {

    }

    override fun hideLoding() {

    }

    override fun onError(message: String) {

    }

}
