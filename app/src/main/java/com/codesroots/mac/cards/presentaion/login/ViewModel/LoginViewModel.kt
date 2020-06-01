package com.codesroots.hossam.mandoobapp.presentation.login.ViewModel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.codesroots.hossam.mandoobapp.presentation.login.repository.LoginRepository
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.models.LoginData
import com.codesroots.mac.cards.models.Trans
import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo

import java.util.ArrayList

class LoginViewModel : ViewModel() {
     var loginRepository: LoginRepository = LoginRepository()


    var coderesponse = MutableLiveData<Int>()
    var DateRepoCompnay: DataRepo = DataRepo()

    private val error = MutableLiveData<String>()

    var loginResponseLD : MutableLiveData<LoginData>? = null

    init {

        loginResponseLD = MutableLiveData()

    }
    fun register(username:String,password:String,mobile:String) {
        DateRepoCompnay.Register(username,password,mobile,"3",PreferenceHelper.getUserId().toString(),loginResponseLD)
    }
    fun Login(username:String,password:String) {
        DateRepoCompnay.Login(username,password,loginResponseLD)
  }

}