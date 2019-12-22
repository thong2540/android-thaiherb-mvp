package com.tat.thai_herb.ui.editProfile

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.bumptech.glide.Glide
import com.senate_system.tracking_android.ui.dialog.DialogManager
import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.hideKeyboard
import com.tat.thai_herb.listener.FragmentCallBack
import com.tat.thai_herb.ui.editProfile.presenter.EditProfilePresenter
import com.tat.thai_herb.utilty.dialog.DialogInterface
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.fragment_editprofile_sheet.view.*

class DemoBottomSheetFragment : SuperBottomSheetFragment(), EditProfileView.View {

    private lateinit var presenter: EditProfilePresenter
    private lateinit var dialogManager: DialogManager
    private var viewDemo: View? = null

    var image: String? = ""
    var name: String? = ""
    var email: String? = ""

    private var mUri: Uri? = null

    private lateinit var callBack: FragmentCallBack.CalBackEditProfile

    fun bottomSheetListener(callBack: FragmentCallBack.CalBackEditProfile) {
        this.callBack = callBack
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewDemo = inflater.inflate(R.layout.fragment_editprofile_sheet, container, false)
        presenter = EditProfilePresenter(this)
        dialogManager = DialogManager()
        presenter.getData()

        setupView(viewDemo)

        onEvent(viewDemo)

        return viewDemo
    }

    private fun setupView(view: View?) {
        Glide.with(this).load(image).into(view!!.imageView_profile_edit)
        view!!.editTextNameEdit.setText(name)
    }

    private fun onEvent(view: View?) {
        view!!.imageView_profile_edit.setOnClickListener {
            hideKeyboard()
            TedImagePicker.with(context!!)
                .title("เลือกรูปภาพ")
                .start { uri -> showSingleImage(view, uri) }
        }

        view.btnCommit.setOnClickListener {
            hideKeyboard()
            presenter.updateData(
                if (mUri != null) mUri!! else null
                , view!!.editTextNameEdit.text.toString().trim()
                , email!!,
                image!!

            )

        }
    }

    private fun showSingleImage(view: View?, uri: Uri) {
        mUri = uri
        Glide.with(this).load(uri).into(view!!.imageView_profile_edit)
    }

    override fun onSuccess(part: String, name: String) {
        callBack.onSuccess(part, name)
        dialogManager.alertAlert(
            activity!!,
            "แจ้งเตือน",
            "แก้ไขสำเร็จ",
            "ตกลง"
        )

        dialogManager.setOnDialogClickListener(object : DialogInterface.DialogCallBackListener {
            override fun onClickButton(id: Int) {
                if (id == R.id.textConfirm) {
                    hideKeyboard()
                    dismiss()
                }
            }
        })
    }

    override fun showeLoding() {
        viewDemo!!.loadingEdit.startAnimation()
        viewDemo!!.loadingEdit.visibility = View.VISIBLE

    }

    override fun hideLoding() {
        viewDemo!!.loadingEdit.clearAnimation()
        viewDemo!!.loadingEdit.visibility = View.GONE
    }

    override fun onError(message: String) {
        dialogManager.alertAlert(
            activity!!,
            "แจ้งเตือน",
            message,
            "ตกลง"
        )
    }

    override fun getCornerRadius() =
        context!!.resources.getDimension(R.dimen.demo_sheet_rounded_corner)

    override fun getStatusBarColor() =
        Color.DKGRAY
}