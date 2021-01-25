package com.codesroots.mac.cards.presentaion.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codesroots.mac.cards.databinding.RegisterBinding
import com.codesroots.mac.cards.databinding.SendMassageBinding
import com.codesroots.mac.cards.presentaion.MainActivity

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: RegisterBinding =
            DataBindingUtil.inflate(inflater, com.codesroots.mac.cards.R.layout.register, container, false)


        view.context = context as MainActivity



        return view.root;


    }
}