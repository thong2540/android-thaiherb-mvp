package com.tat.thai_herb.ui.editProfile.presenter

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.tat.thai_herb.model.request.User
import com.tat.thai_herb.ui.editProfile.EditProfileView
import java.util.*

class EditProfilePresenter(private val view: EditProfileView.View) {
    private var storageReference: StorageReference? = null
    private var databaseReference: DatabaseReference? = null
    private var firebaseUser: FirebaseUser? = null

    init {
        storageReference = FirebaseStorage.getInstance().reference
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    fun updateData(filePath: Uri, name: String, email: String) {
        view.showeLoding()
        if (filePath != null) {
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    setData(name, email, downloadUri.toString())
                } else {

                }
            }?.addOnFailureListener {

            }
        } else {

        }
    }

    private fun setData(name: String, email: String, part: String) {
        databaseReference =
            FirebaseDatabase.getInstance().getReference("NewUser").child(firebaseUser!!.uid)

        databaseReference!!.setValue(
            User(
                name,
                email,
                part,
                firebaseUser!!.uid,
                "0",
                "0",
                "0"
            )
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                view.hideLoding()
                view.onSuccess(part,name)
            } else {
                view.hideLoding()
                view.onError("ไม่สามารถแก้ไขข้อมูลได้ กรุณาตรวจสอบข้อมูลของท่านอีกครั้้ง")
            }
        }
    }

}