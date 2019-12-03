package com.tat.thai_herb.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.tat.thai_herb.R
import com.tat.thai_herb.listener.RecyclerViewCallBack
import com.tat.thai_herb.model.respone.DataList
import com.tat.thai_herb.model.respone.Herb
import com.tat.thai_herb.model.respone.SymptomList
import com.tat.thai_herb.ui.home.adapter.HomeAdapter
import com.tat.thai_herb.ui.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(),HomeView.View {

    private lateinit var presenter: HomePresenter
    private lateinit var homeAdapter: HomeAdapter
    var dataList: List<DataList> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        presenter = HomePresenter(this)
        homeAdapter = HomeAdapter(context!!)

        presenter.getDataHerb()

        setupRecyclerView(view)
        onEvent(view)

        return view
    }

    private fun setupRecyclerView(view: View?) {
        view!!.mainRecyclerViewHome.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL!!,false)
        view.mainRecyclerViewHome.isNestedScrollingEnabled = false
        view.mainRecyclerViewHome.adapter = homeAdapter
    }

    private fun onEvent(view: View?) {
        view!!.imageViewDelete.setOnClickListener {
            view.editTextSearchHome.setText("")
        }

        homeAdapter.setOnDataRecyclerViewListener(object :RecyclerViewCallBack {
            override fun onClickItem(position: Int) {

            }

            override fun onPresentData(data: SymptomList) {

            }

        })
    }

    override fun itemDataHerb(item: List<DataList>) {
        dataList = item
        homeAdapter.itemList = item
        homeAdapter.notifyDataSetChanged()
    }

    override fun showeLoding() {

    }

    override fun hideLoding() {

    }

    override fun onError(message: String) {

    }

}
