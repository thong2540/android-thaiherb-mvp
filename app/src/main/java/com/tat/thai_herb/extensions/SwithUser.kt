package com.tat.thai_herb.extensions

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.senate_system.tracking_android.ui.dialog.DialogManager
import com.tat.thai_herb.R
import com.tat.thai_herb.ui.login.LoginActivity
import com.tat.thai_herb.ui.main.MainActivity
import com.tat.thai_herb.utilty.dialog.DialogInterface


fun Fragment.logout() {

    var freebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val dialogManager = DialogManager()

    dialogManager.alertQuest(
        activity!!,
        "แจ้งเตือน",
        "ต้องการออกจากระบบ",
        "ตกลง"
    )

    dialogManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
        override fun onClickButton(id: Int) {
            if (id == R.id.textLogin) {
                freebaseAuth!!.signOut()
                freebaseAuth!!.addAuthStateListener {
                    if (freebaseAuth!!.currentUser == null) {
                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                        val main = MainActivity()
                        main.finish()
                    }
                }
            }
        }
    })
}