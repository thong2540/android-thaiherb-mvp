package com.tat.thai_herb.ui.pagemenu


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.logout
import com.tat.thai_herb.listener.FragmentCallBack
import com.tat.thai_herb.model.respone.UserInfo
import com.tat.thai_herb.ui.about.AboutActivity
import com.tat.thai_herb.ui.editProfile.DemoBottomSheetFragment
import com.tat.thai_herb.ui.help.HelpActivity
import com.tat.thai_herb.ui.pagemenu.presenter.MenuPresenter
import com.tat.thai_herb.ui.profile.ProfileActivity
import com.tat.thai_herb.ui.resetpassword.ResetPassword
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment(), MenuView.View {

    private lateinit var presenter: MenuPresenter
    private var viewMenu: View? = null
    private var user: UserInfo? = null
    private lateinit var sheet: DemoBottomSheetFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMenu = inflater.inflate(R.layout.fragment_menu, container, false)
        sheet = DemoBottomSheetFragment()

        onEvent(viewMenu)

        return viewMenu
    }

    private fun onEvent(view: View?) {
        view!!.profile_click_menu.setOnClickListener {
            val intent = Intent(context!!, ProfileActivity::class.java)
            intent.putExtra("image",user!!.imgURL)
            intent.putExtra("name",user!!.username)
            intent.putExtra("email",user!!.email)
            intent.putExtra("scoreFirst",user!!.scoreFirst)
            intent.putExtra("scoreSecond",user!!.scoreSecond)
            intent.putExtra("scoreThird",user!!.scoreThird)
            startActivity(intent)
        }

        view.editpass_click.setOnClickListener {
            val intent = Intent(context!!, ResetPassword::class.java)
            startActivity(intent)
        }

        view.help_click.setOnClickListener {
            //edit profile
            sheet.image = user!!.imgURL
            sheet.name = user!!.username
            sheet.email = user!!.email
            sheet.show(activity!!.supportFragmentManager, "DemoBottomSheetFragment")
        }

        view.about_click.setOnClickListener {
            val intent = Intent(context!!, AboutActivity::class.java)
            startActivity(intent)
        }

        view.logout_click.setOnClickListener {
            this.logout()
        }

        sheet.bottomSheetListener(object : FragmentCallBack.CalBackEditProfile {
            override fun onSuccess(image: String, name: String) {
                presenter.getUserInfo()
            }
        })
    }

    override fun responeUserInfo(dataUser: UserInfo) {
        user = dataUser
        viewMenu!!.title_menu.text = dataUser!!.username
        viewMenu!!.des_menu.text = dataUser!!.email
        if (dataUser!!.imgURL != "default") {
            Glide.with(context!!)
                .load(dataUser!!.imgURL)
                .into(viewMenu!!.image_menu)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter = MenuPresenter(this)
        presenter.getUserInfo()
    }
}
