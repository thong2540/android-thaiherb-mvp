package com.tat.thai_herb.ui.pagemenu.presenter

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.tat.thai_herb.model.respone.UserInfo
import com.tat.thai_herb.ui.pagemenu.MenuView

class MenuPresenter(private val view: MenuView.View) {

    private var databaseReference: DatabaseReference? = null
    private var firebaseUser: FirebaseUser? = null
    internal var user: UserInfo? = null

    init {
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    fun getUserInfo(){
        databaseReference = FirebaseDatabase.getInstance().getReference("NewUser").child(firebaseUser!!.uid)
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(UserInfo::class.java)
                view.responeUserInfo(user!!)
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }
}