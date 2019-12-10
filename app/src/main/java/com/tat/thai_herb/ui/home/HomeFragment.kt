package com.tat.thai_herb.ui.home


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.logout
import com.tat.thai_herb.listener.RecyclerViewCallBack
import com.tat.thai_herb.model.respone.DataList
import com.tat.thai_herb.model.respone.SymptomList
import com.tat.thai_herb.ui.description.DescriptionActivity
import com.tat.thai_herb.ui.detaimain.DetailMainActivity
import com.tat.thai_herb.ui.home.adapter.HomeAdapter
import com.tat.thai_herb.ui.home.adapter.SliderAdapter
import com.tat.thai_herb.ui.home.presenter.HomePresenter
import com.tat.thai_herb.ui.search.SearchActivity
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.header_toolbar_main.view.*


class HomeFragment : Fragment(), HomeView.View {

    private var viewHome: View? = null
    private lateinit var presenter: HomePresenter
    private lateinit var homeAdapter: HomeAdapter
    var dataList: List<DataList> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewHome = inflater.inflate(R.layout.fragment_home, container, false)
        presenter = HomePresenter(this)
        homeAdapter = HomeAdapter(context!!)

        presenter.getDataHerb()

        setupRecyclerView(viewHome)
        setSliderView(viewHome)
        onEvent(viewHome)

        return viewHome
    }

    private fun setSliderView(view: View?) {
        val adapter = SliderAdapter(context!!)
        adapter.mCount = 5

        view!!.imageSlider.sliderAdapter = adapter

        view.imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
        view.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        view.imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        view.imageSlider.indicatorSelectedColor = Color.WHITE
        view.imageSlider.indicatorUnselectedColor = Color.GRAY
        view.imageSlider.scrollTimeInSec = 2
        view.imageSlider.startAutoCycle()

        view.imageSlider.setOnIndicatorClickListener {
            view.imageSlider.currentPagePosition = it
        }
    }

    private fun setupRecyclerView(view: View?) {
        view!!.mainRecyclerViewHome.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
        view.mainRecyclerViewHome.isNestedScrollingEnabled = false
        view.mainRecyclerViewHome.adapter = homeAdapter
    }

    private fun onEvent(view: View?) {

        view!!.editTextSearchHome.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        homeAdapter.setOnDataRecyclerViewListener(object : RecyclerViewCallBack {
            override fun onClickItem(position: Int) {
                val intent = Intent(context, DetailMainActivity::class.java)
                intent.putExtra("Title", dataList[position].title)
                intent.putExtra("key", dataList[position].key)
                startActivity(intent)
            }

            override fun onPresentData(data: SymptomList) {
                val intent = Intent(context, DescriptionActivity::class.java)
                intent.putExtra("ImgHerb", data.image)
                intent.putExtra("TitleHerb", data.title)
                intent.putExtra("DesHerb", data.description)
                startActivity(intent)
            }

        })
    }

    override fun itemDataHerb(item: List<DataList>) {
        viewHome!!.homeErrorText.visibility = View.GONE

        dataList = item
        homeAdapter.itemList = item
        homeAdapter.notifyDataSetChanged()
    }

    override fun showeLoding() {
        viewHome!!.loadingHome.startAnimation()
        viewHome!!.loadingHome.visibility = View.VISIBLE
        viewHome!!.pageHome.visibility = View.GONE

    }

    override fun hideLoding() {
        viewHome!!.loadingHome.clearAnimation()
        viewHome!!.loadingHome.visibility = View.GONE
        viewHome!!.pageHome.visibility = View.VISIBLE
    }

    override fun onError(message: String) {
        viewHome!!.homeErrorText.visibility = View.VISIBLE
        viewHome!!.homeErrorText.text = message
    }

}
