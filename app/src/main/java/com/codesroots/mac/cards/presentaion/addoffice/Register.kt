package com.codesroots.mac.cards.presentaion.addoffice

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.codesroots.hossam.mandoobapp.presentation.login.ViewModel.LoginViewModel
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.DataLayer.usecases.checkUserLogin
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.isInternetConnectionAvailable
import com.codesroots.mac.cards.presentaion.snack
import com.codesroots.mac.cards.presentaion.toast
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin.btnLogin
import kotlinx.android.synthetic.main.activity_signin.etPassword
import kotlinx.android.synthetic.main.activity_signin.etUsername

class Register : AppCompatActivity()  {
    var network_enabled = false
    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceHelper(this)
        setContentView(R.layout.activity_register)
        animation()


      //  if (checkUserLogin(this))    startActivity(Intent(this  , MainActivity::class.java))



        btnLogin.setOnClickListener {
            if (!isInternetConnectionAvailable(this)) "رجاء تأكد من اتصالك بالانترنت".snack(window.decorView.rootView)
            viewModel.register(etUsername.text.toString(),etPassword.text.toString(),mobile.text.toString())

        }

        viewModel.loginResponseLD?.observe(this , Observer {


            if (it.token == null){
                it.message!!.snack(window.decorView.rootView)
                Toast.makeText(this, "موجود من قبل", Toast.LENGTH_LONG)

            }else {
//                PreferenceHelper.setToken(it.token,this)
//                PreferenceHelper.setUserId(it.userid!!)
//                PreferenceHelper.setUsername(it.username!!)
//
//                PreferenceHelper.setUserGroupId(it.groupid!!.toInt())
//                FirebaseMessaging.getInstance().subscribeToTopic(PreferenceHelper.getUserGroupId().toString())
//                FirebaseMessaging.getInstance().subscribeToTopic(PreferenceHelper.getUserId().toString())
                "تم إضافة المكتب بنجاح".snack(window.decorView.rootView)
                Toast.makeText(this, "تم إضافة المكتب بنجاح", Toast.LENGTH_LONG)
             //   startActivity(Intent(this, MainActivity::class.java))
            }
        })

        viewModel.coderesponse.observe(this , Observer {
            if (it!=200) "Registration failed ".snack(window.decorView.rootView)
        })

    }


    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
    private fun animation(){

        val slide = TransitionInflater.from(this).inflateTransition(R.transition.slide_activity)
        getWindow().setEnterTransition(slide);
    }
}
