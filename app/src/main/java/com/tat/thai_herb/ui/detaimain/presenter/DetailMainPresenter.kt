package com.tat.thai_herb.ui.detaimain.presenter

import com.google.firebase.database.*
import com.tat.thai_herb.model.respone.Detail
import com.tat.thai_herb.ui.detaimain.DetailMainView

class DetailMainPresenter(private val view: DetailMainView.View) {

    private var databaseReference: DatabaseReference? = null
    private val listHeader = arrayListOf<String>()

    internal var detail: Detail? = null
    private val detailList = arrayListOf<Detail>()

    fun getDataHeader(key: String) {
        listHeader.clear()
        databaseReference = FirebaseDatabase.getInstance().getReference("Herbs").child(key)
        databaseReference!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    listHeader.add(snapshot.key.toString())
                }
                view.responeHeader(listHeader)
            }

            override fun onCancelled(p0: DatabaseError) {
                view.onError(p0.toException().message.toString())
            }

        })
    }

    fun getDataList(childKey: String, key: String){
        detailList.clear()
        view.showeLoding()
        databaseReference = FirebaseDatabase.getInstance().getReference("Herbs").child(childKey).child(key)
        databaseReference!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    detail = snapshot.getValue(Detail::class.java)
                    detailList.add(detail!!)
                }
                view.hideLoding()
                view.responeList(detailList)
            }

            override fun onCancelled(p0: DatabaseError) {
                view.hideLoding()
                view.onError(p0.toException().message.toString())
            }

        })
    }
}