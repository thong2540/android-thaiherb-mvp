package com.tat.thai_herb.ui.register.persenter

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.tat.thai_herb.model.request.User
import com.tat.thai_herb.ui.register.RegisterView
import java.util.*

class RegisterPersenter(private val view: RegisterView.View) {

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private var databaseReference: DatabaseReference? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null

    init {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun uploadImage(filePath: Uri) {
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
                    view.saveImageSuccess(downloadUri.toString())
                } else {
                    view.onError("อัพโหลดรูปภาพไม่สำเร็จ !")
                }
            }?.addOnFailureListener {
                view.saveImageFail(it)
            }
        } else {
            view.onError("กรุณาอัพโหลดภาพ")
        }
    }

    fun register(name: String, email: String, password: String, image: String) {
        view.showeLoding()
        firebaseAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseUser = firebaseAuth?.currentUser
                    var userId: String? = null
                    if (firebaseUser != null) userId = firebaseUser?.uid!!

                    databaseReference = FirebaseDatabase.getInstance().getReference("NewUser").child(userId!!)

                    databaseReference!!.setValue(
                        User(
                            name,
                            email,
                            image,
                            userId,
                            "0",
                            "0",
                            "0"
                        )
                    )
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                view.hideLoding()
                                view.registerSuccess()
                            } else {
                                view.onError("ไม่สามารถสมัครสมาชิกได้ กรุณาตรวจสอบอีกครั้ง")
                            }
                        }
                }
            }
    }
}
