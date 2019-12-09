package com.tat.thai_herb.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.hideKeyboard
import com.tat.thai_herb.listener.SearchViewCallBack
import com.tat.thai_herb.model.respone.SearchList
import com.tat.thai_herb.ui.description.DescriptionActivity
import com.tat.thai_herb.ui.search.adapter.SearchAdapter
import com.tat.thai_herb.ui.search.presenter.SearchPresenter
import com.tat.thai_herb.utilty.StatusbarManager
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.android.synthetic.main.header_toolbar_search.view.*

class SearchActivity : AppCompatActivity(), SearchView.View {

    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: SearchAdapter
    private var data: List<SearchList> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        StatusbarManager.darkenStatusBar(this, R.drawable.bg_toolbar)
        presenter = SearchPresenter(this)

        setupRecyclerView()
        onEvent()
    }

    private fun setupRecyclerView() {
        adapter = SearchAdapter()
        search_recyclerview.setHasFixedSize(true)
        search_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL!!, false)
        search_recyclerview.adapter = adapter
    }

    private fun onEvent() {
        heard_search.imageViewDelete_search.setOnClickListener {
            heard_search.editTextSearch.setText("")
            presenter.searchUsers("")
        }

        heard_search.textViewDisSearch.setOnClickListener {
            this.hideKeyboard()
            finish()
        }

        heard_search.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                presenter.searchUsers(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        adapter.setOnClickResponseCallBack(object : SearchViewCallBack {
            override fun onClickItem(position: Int) {
                hideKeyboard()
                val intent = Intent(this@SearchActivity,DescriptionActivity::class.java)
                intent.putExtra("ImgHerb", data[position].image)
                intent.putExtra("TitleHerb", data[position].title)
                intent.putExtra("DesHerb", data[position].description)
                startActivity(intent)
            }
        })
    }

    override fun responeSearch(list: List<SearchList>) {
        data = list
        if (list.isEmpty()) {
            showeEror.visibility = View.VISIBLE
            search_recyclerview.visibility = View.GONE
        }else{
            showeEror.visibility = View.GONE
            search_recyclerview.visibility = View.VISIBLE
        }
        adapter.mData = list
        adapter.notifyDataSetChanged()
    }

    override fun showeLoding() {

    }

    override fun hideLoding() {

    }

    override fun onError(message: String) {

    }
}
