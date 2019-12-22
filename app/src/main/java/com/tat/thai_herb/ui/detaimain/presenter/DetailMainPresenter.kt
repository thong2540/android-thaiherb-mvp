package com.tat.thai_herb.ui.detaimain.presenter

import android.os.Handler
import androidx.annotation.NonNull
import com.google.firebase.database.*
import com.tat.thai_herb.model.respone.DataList
import com.tat.thai_herb.model.respone.Detail
import com.tat.thai_herb.ui.detaimain.DetailMainView

class DetailMainPresenter(private val view: DetailMainView.View) {

    private var databaseReference: DatabaseReference? = null
    private val listHeader = arrayListOf<String>()

    internal var detail: Detail? = null
    private val detailList = arrayListOf<Detail>()

    internal var data: DataList? = null
    private var listData = arrayListOf<DataList>()

    fun getDataHeader(key: String) {
        listHeader.clear()
        val query =
            FirebaseDatabase.getInstance().getReference("data_herb").child("data")
                .orderByChild("key")
                .equalTo(key)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    data = snapshot.getValue(DataList::class.java)!!
                    listData.add(data!!)
                }
                listData[0].system.forEach {
                    listHeader.add(it.symptom_name)
                }
                view.responeHeader(listHeader)
            }

            override fun onCancelled(@NonNull databaseError: DatabaseError) {
                view.onError(databaseError.toException().message.toString())
            }
        })
    }

    fun getDataList(position: Int) {
        detailList.clear()
        view.showeLoding()
        listData[0].system[position].symptom_list.forEach {
            detailList.add(
                Detail(
                    "",
                    it.description,
                    it.image,
                    it.title
                )
            )
        }
        Handler().postDelayed({
            view.hideLoding()
            view.responeList(detailList)
        }, 500)
    }
}