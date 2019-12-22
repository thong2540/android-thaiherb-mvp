package com.tat.thai_herb.ui.detaimain

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.thai_herb.R
import com.tat.thai_herb.listener.ActionClickHeader
import com.tat.thai_herb.model.respone.Detail
import com.tat.thai_herb.ui.description.DescriptionActivity
import com.tat.thai_herb.ui.detaimain.adapter.HeaderAdapter
import com.tat.thai_herb.ui.detaimain.adapter.ListDataAdapter
import com.tat.thai_herb.ui.detaimain.presenter.DetailMainPresenter
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_detail_main.*

class DetailMainActivity : AppCompatActivity(), DetailMainView.View {

    private lateinit var presenter: DetailMainPresenter
    private var headerAdapter: HeaderAdapter? = null
    private var listDataAdapter: ListDataAdapter? = null
    private var childKey: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_main)
        StatusbarManager.darkenStatusBarColor(this, R.drawable.bg_toolbar)
        presenter = DetailMainPresenter(this)
        //เช็ค null การส่งค่าผ่าน intent กันบัค
        if (intent == null) return

        setOnData()

        setRecyclerView()

        onEvent()
    }

    private fun setRecyclerView() {
        //header
        headerAdapter = HeaderAdapter()
        recyclerviewHeader.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHeader.setHasFixedSize(true)
        recyclerviewHeader.isNestedScrollingEnabled = false
        headerAdapter!!.mContext = this
        recyclerviewHeader.adapter = headerAdapter
        //list
        listDataAdapter = ListDataAdapter()
        recyclerviewData.setHasFixedSize(true)
        recyclerviewData.layoutManager = GridLayoutManager(this, 2)
        listDataAdapter!!.mContext = this
        recyclerviewData.adapter = listDataAdapter
    }

    private fun setOnData() {
        val type = intent
        if (type.getStringExtra("key") != "") {
            childKey = type.getStringExtra("key")
            presenter.getDataHeader(type.getStringExtra("key"))
        }
        if (type.getStringExtra("Title") != "") {
            textTitle.text = type.getStringExtra("Title")
        }
    }

    private fun onEvent() {
        textViewCancel.setOnClickListener {
            finish()
        }

        headerAdapter!!.setOnClickHeader(object : ActionClickHeader {
            override fun onClickList(position: Int) {
                presenter.getDataList(
                    position
                )
            }

            override fun onClickItem(data: Detail) {

            }
        })

        listDataAdapter!!.setOnClickResponseCallBack(object : ActionClickHeader {
            override fun onClickItem(data: Detail) {
                setPresentDescriptionHerb(data)
            }

            override fun onClickList(position: Int) {

            }
        })

    }

    private fun setPresentDescriptionHerb(data: Detail) {
        val intent = Intent(this, DescriptionActivity::class.java)
        intent.putExtra("ImgHerb", data.image)
        intent.putExtra("TitleHerb", data.title)
        intent.putExtra("DesHerb", data.description)
        startActivity(intent)
    }

    override fun responeHeader(listHeader: List<String>) {
        headerAdapter!!.listHeader = listHeader
        headerAdapter!!.notifyDataSetChanged()

        presenter.getDataList(
            0
        )

    }

    override fun responeList(listData: List<Detail>) {
        listDataAdapter!!.mData = listData
        listDataAdapter!!.notifyDataSetChanged()
    }

    override fun showeLoding() {
        recyclerviewData.visibility = View.GONE
        loadingMainDes.startAnimation()
        loadingMainDes.visibility = View.VISIBLE
    }

    override fun hideLoding() {
        loadingMainDes.clearAnimation()
        loadingMainDes.visibility = View.GONE
        recyclerviewData.visibility = View.VISIBLE
    }

    override fun onError(message: String) {

    }
}
