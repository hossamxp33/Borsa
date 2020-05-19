package com.codesroots.mac.cards.presentaion.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.change_password.view.*


class changePassword : Fragment()  {
    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        val view= inflater.inflate(com.codesroots.mac.cards.R.layout.change_password, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        view.btnchangepw.setOnClickListener {
            Toast.makeText(context, view.etPassword.text.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(context, view.rePassword.text.toString(), Toast.LENGTH_SHORT).show()

            if (view.etPassword.text.toString() == view.rePassword.text.toString()) {
                viewModel.ChangePassword(view.etPassword.text.toString())

                viewModel.EditResponseLD?.observe(this, Observer {
                    if (it.success == true) {
                        Toast.makeText(context, "تم التغيير", Toast.LENGTH_SHORT).show()


                    }

                })
            }else {

                Toast.makeText(context, "غير متطابق", Toast.LENGTH_SHORT).show()

            }
        }

        return view
    }

}
