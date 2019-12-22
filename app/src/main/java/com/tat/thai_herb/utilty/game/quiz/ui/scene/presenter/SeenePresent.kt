package com.tat.thai_herb.utilty.game.quiz.ui.scene.presenter

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tat.thai_herb.utilty.game.quiz.ui.scene.SceneView

class SeenePresent(private val view: SceneView.View) {

    private var databaseReference: DatabaseReference? = null
    private var firebaseUser: FirebaseUser? = null

    init {
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    fun setScoreQuiz(score: Int) {
        databaseReference = FirebaseDatabase.getInstance().getReference("NewUser").child(firebaseUser!!.uid).child("scoreThird")
        databaseReference!!.setValue(score).addOnCompleteListener {
            if (it.isSuccessful) {
                view.isSuccess()
            }
        }
    }

    fun setScoreMemory(score: Int) {
        databaseReference = FirebaseDatabase.getInstance().getReference("NewUser").child(firebaseUser!!.uid).child("scoreSecond")
        databaseReference!!.setValue(score).addOnCompleteListener {
            if (it.isSuccessful) {
                view.isSuccess()
            }
        }
    }
}