package com.tat.thai_herb.ui.pagemenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.tat.thai_herb.R
import com.tat.thai_herb.extensions.logout
import com.tat.thai_herb.model.respone.UserInfo
import com.tat.thai_herb.ui.pagemenu.presenter.MenuPresenter
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment(),MenuView.View {

    private lateinit var presenter: MenuPresenter
    private var viewMenu: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMenu = inflater.inflate(R.layout.fragment_menu, container, false)
        presenter = MenuPresenter(this)
        presenter.getUserInfo()

        onEvent(viewMenu)

        return viewMenu
    }

    private fun onEvent(view: View?) {
        view!!.profile_click_menu.setOnClickListener {

        }

        view.editProfile_click.setOnClickListener {

        }

        view.editpass_click.setOnClickListener {

        }

        view.about_click.setOnClickListener {

        }

        view.logout_click.setOnClickListener {
            this.logout()
        }
    }

    override fun responeUserInfo(dataUser: UserInfo) {
        viewMenu!!.title_menu.text = dataUser!!.username
        viewMenu!!.des_menu.text = dataUser!!.email
        if (dataUser!!.imgURL != "default"){
            Glide.with(context!!).load(dataUser!!.imgURL).into(viewMenu!!.image_menu)
        }
    }
}
