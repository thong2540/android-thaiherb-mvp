package com.tat.thai_herb.ui.home.presenter

import com.google.firebase.database.*
import com.tat.thai_herb.model.respone.Herb
import com.tat.thai_herb.ui.home.HomeView


class HomePresenter(private val view: HomeView.View) {

    private var databaseReference: DatabaseReference? = null
    internal var herb: Herb? = null

    init {
        databaseReference = FirebaseDatabase.getInstance().getReference("data_herb")
    }

    fun getDataHerb() {
        view.showeLoding()
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                view.hideLoding()
                if (p0.exists()) {
                    herb = p0.getValue(Herb::class.java)
                    view.itemDataHerb(herb?.data!!)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                view.hideLoding()
                view.onError(p0.toException().message.toString())
            }
        })
    }
}