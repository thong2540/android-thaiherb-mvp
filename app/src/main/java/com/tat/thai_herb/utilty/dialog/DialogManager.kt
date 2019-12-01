package com.senate_system.tracking_android.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.TextView
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.dialog.DialogInterface

class DialogManager {

    private var alertdialog : Dialog? = null
    private lateinit var dialogInterface: DialogInterface.DialogCallBackListener

    fun setOnDialogClickListener(listener: DialogInterface.DialogCallBackListener) {
        dialogInterface = listener
    }

    fun alertQuestLogin(activity: Activity, Title: String?, Message: String?) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_quest)
        alertdialog!!.setCancelable(false)

        val textLogin = alertdialog!!.findViewById<View>(R.id.textLogin) as TextView
        val textCancel = alertdialog!!.findViewById<View>(R.id.textCancel) as TextView

        textLogin.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }

        textCancel.setOnClickListener {
            if (alertdialog != null)
                alertdialog!!.dismiss()
        }
        alertdialog!!.show()
    }

    fun alertQuest(activity: Activity, Title: String, Message: String, TextConfirm: String) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_quest)
        alertdialog!!.setCancelable(false)

        val textLogin = alertdialog!!.findViewById<View>(R.id.textLogin) as TextView
        val textCancel = alertdialog!!.findViewById<View>(R.id.textCancel) as TextView
        val alertTitle = alertdialog!!.findViewById<View>(R.id.alertTitle) as TextView
        val alertContent = alertdialog!!.findViewById<View>(R.id.alertContent) as TextView

        alertTitle.text = Title
        alertContent.text = Message
        textLogin.text = TextConfirm

        textLogin.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }

        textCancel.setOnClickListener {
            if (alertdialog != null)
                alertdialog!!.dismiss()
        }
        alertdialog!!.show()
    }

    fun alertAlert(activity: Activity, Title: String?, Message: String?, TextConfirm: String?) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_alert)
        alertdialog!!.setCancelable(false)

        val alertTitle = alertdialog!!.findViewById<View>(R.id.alertTitle) as TextView
        val alertContent = alertdialog!!.findViewById<View>(R.id.alertContent) as TextView
        val textConfirm = alertdialog!!.findViewById<View>(R.id.textConfirm) as TextView

        alertTitle.text = Title
        alertContent.text = Message
        textConfirm.text = TextConfirm

        textConfirm.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }
        alertdialog!!.show()
    }

}