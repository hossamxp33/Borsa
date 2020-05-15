package com.codesroots.mac.cards.presentaion.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class changePassword : Fragment()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        val view= inflater.inflate(com.codesroots.mac.cards.R.layout.change_password, container, false)



        return view
    }

}
