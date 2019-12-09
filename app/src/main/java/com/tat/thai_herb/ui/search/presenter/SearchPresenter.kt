package com.tat.thai_herb.ui.search.presenter

import androidx.annotation.NonNull
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tat.thai_herb.model.respone.SearchList
import com.tat.thai_herb.ui.search.SearchView


class SearchPresenter(private val view: SearchView.View) {

    internal var searchData: SearchList? = null
    private val list = arrayListOf<SearchList>()

    fun searchUsers(s: String) {
        list.clear()
        val query =
            FirebaseDatabase.getInstance().getReference("data_herb").child("search")
                .orderByChild("title")
                .startAt(s)
                .endAt(s + "\uf8ff")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    searchData = snapshot.getValue(SearchList::class.java)!!
                    list.add(searchData!!)
                }
                view.responeSearch(list)
            }

            override fun onCancelled(@NonNull databaseError: DatabaseError) {}
        })
    }
}