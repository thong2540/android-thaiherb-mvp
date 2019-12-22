package com.tat.thai_herb.utilty.game.quiz.utilty.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.tat.thai_herb.R
import com.tat.thai_herb.utilty.dialog.DialogInterface

class DialogGameManager {

    private var alertdialog : Dialog? = null
    private lateinit var dialogInterface: DialogInterface.DialogCallBackListener

    fun setOnDialogClickListener(listener: DialogInterface.DialogCallBackListener) {
        dialogInterface = listener
    }

    fun alertError(activity: Activity, Title: String?) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_error)
        alertdialog!!.setCancelable(false)

        val alertTitle = alertdialog!!.findViewById<View>(R.id.alertTitle) as TextView

        alertTitle.text = Title
        alertdialog!!.show()

        Handler().postDelayed({
            alertdialog!!.dismiss()
        },2000)
    }

    fun alertSuccess(activity: Activity, Title: String?, Message: String?) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_success)
        alertdialog!!.setCancelable(false)

        val alertTitle = alertdialog!!.findViewById<View>(R.id.alertTitleSuccess) as TextView
        val alertContent = alertdialog!!.findViewById<View>(R.id.alertDescriptionSuccess) as TextView
        val textConfirm = alertdialog!!.findViewById<View>(R.id.clickSuccess) as ImageView

        alertTitle.text = Title
        alertContent.text = Message

        textConfirm.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }
        alertdialog!!.show()
    }

    fun alertTime(activity: Activity) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_time)
        alertdialog!!.setCancelable(false)

        alertdialog!!.show()

        Handler().postDelayed({
            alertdialog!!.dismiss()
        },3000)
    }

    fun alertQuiz(activity: Activity, score: String?, isHiden: Boolean) {

        alertdialog = Dialog(activity)
        alertdialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertdialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertdialog!!.setContentView(R.layout.dialog_quiz)
        alertdialog!!.setCancelable(false)

        val alertContent = alertdialog!!.findViewById<View>(R.id.alertDescriptionQuiz) as TextView
        val textConRestart = alertdialog!!.findViewById<View>(R.id.Restart) as ImageView
        val textConHome = alertdialog!!.findViewById<View>(R.id.Home) as ImageView
        val textConPlay = alertdialog!!.findViewById<View>(R.id.Play) as ImageView

        alertContent.text = score

        if (isHiden)
            textConPlay.visibility = View.GONE

        textConRestart.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }

        textConHome.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }

        textConPlay.setOnClickListener { view ->
            if(dialogInterface != null)
                dialogInterface.onClickButton(view.id)

            if (alertdialog != null)
                alertdialog!!.dismiss()
        }
        alertdialog!!.show()

    }

}