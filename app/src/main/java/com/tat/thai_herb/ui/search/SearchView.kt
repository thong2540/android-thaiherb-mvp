package com.tat.thai_herb.ui.search

import com.tat.thai_herb.model.respone.SearchList
import com.tat.thai_herb.utilty.BasePresenter

class SearchView {
    interface View : BasePresenter {
        fun responeSearch(list: List<SearchList>)
    }
}