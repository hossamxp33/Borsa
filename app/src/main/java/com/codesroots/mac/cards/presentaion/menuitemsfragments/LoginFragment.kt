package com.codesroots.mac.cards.presentaion.menuitemsfragments

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.databinding.LoginFragmentBinding
import com.codesroots.mac.cards.presentaion.ClickHandler
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.borsadetails.Details
import com.codesroots.mac.cards.presentaion.mainfragment.mainFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityManager
import android.R



class LoginFragment:Fragment() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OneSignal.sendTag("id", "0")
        PreferenceHelper.ClearUserID()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: LoginFragmentBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.login_fragment, container, false
            )
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);


        val layout = (context as MainActivity).appBarLayout2
        layout.visibility = View.GONE

        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        view.loginbtn.setOnClickListener {
            viewModel.Login(
                android_id,
                view.id.text.toString(),
                view.pw.text.toString()
            )

            viewModel.LoginLD?.observe(this, Observer {
                if (it != 0) {
                    PreferenceHelper.setUserId(it)
                    PreferenceHelper.setUserIdForMsg(it)
                    OneSignal.sendTag("id", "1")

                    if (!(view.id.text.isEmpty() || view.pw.text.isEmpty()))
                    {

                        Toast.makeText(context, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show()
                        val bundle = Bundle()
                        val frag = mainFragment()
                        frag.arguments = bundle
                        ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
                            com.codesroots.mac.cards.R.anim.ttb,0, 0,0)
                            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
                    }
                }
                    else{
                //        val data = it


                    Toast.makeText(context, "خطأ بكلمة المرور او اسم المستخدم", Toast.LENGTH_SHORT).show()


                }


            })
        }
        view.context = context as MainActivity
         view.listener = ClickHandler()


        return view.root;


    }



}

