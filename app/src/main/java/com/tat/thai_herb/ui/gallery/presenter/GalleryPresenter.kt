package com.tat.thai_herb.ui.gallery.presenter

import com.google.firebase.database.*
import com.tat.thai_herb.model.respone.ContentImage
import com.tat.thai_herb.ui.gallery.GalleryView

class GalleryPresenter(private val view: GalleryView.View){

    private var databaseReference: DatabaseReference? = null
    internal var part: ContentImage? = null
    private var listPart = arrayListOf<ContentImage>()

    init {
        databaseReference = FirebaseDatabase.getInstance().getReference("AllImage")
    }

    fun getListImage(){
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    part = snapshot.getValue(ContentImage::class.java)
                    listPart.add(part!!)
                }
                view.responeImage(listPart)
            }

            override fun onCancelled(p0: DatabaseError) {
                view.onError(p0.toException().message.toString())
            }
        })
    }
}