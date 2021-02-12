package com.codesroots.mac.cards.presentaion.massagefragment

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
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.SendMassageBinding
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.borsadetails.Details
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menuitemsfragments.LoginFragment

class MassageFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: SendMassageBinding =
            DataBindingUtil.inflate(
                inflater, com.codesroots.mac.cards.R.layout.send_massage, container, false
            )
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(), Settings.Secure.ANDROID_ID);

        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)
        view.sendBtn.setOnClickListener {
            viewModel.post_massage(
                android_id,
                PreferenceHelper.getUserIdForMsg().toString(),
                view.name.text.toString(),
                view.number.text.toString(),
                view.title.text.toString(),
                view.massage.text.toString()
            )
            viewModel.SendMassageLD?.observe(this, Observer {
                if (it ==  1) {
                    if (!(view.name.text.isEmpty() || view.number.text.isEmpty() || view.title.text.isEmpty()|| view.massage.text.isEmpty()))
                    Toast.makeText(context, "تم ارسال الرسالة بنجاح", Toast.LENGTH_SHORT).show()
                    val bundle = Bundle()

                    val frag = LoginFragment()
                    frag.arguments = bundle
                    ( context as MainActivity).supportFragmentManager!!.beginTransaction().setCustomAnimations(
                        R.anim.ttb,0, 0,0)
                        .replace(R.id.main_frame, frag).addToBackStack(null).commit()

                }
                else{
                    Toast.makeText(context, "خطأ", Toast.LENGTH_SHORT).show()
                }

            })
        }
        view.context = context as MainActivity



        return view.root;


    }



}

