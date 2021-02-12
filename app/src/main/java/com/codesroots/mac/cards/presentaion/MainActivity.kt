package com.codesroots.mac.cards.presentaion

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.opengl.ETC1.getHeight
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Update
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.codesroots.mac.cards.DataLayer.helper.MyService
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.ActivityMainBinding
import com.codesroots.mac.cards.databinding.MainFragmentBinding
import com.codesroots.mac.cards.presentaion.mainfragment.mainFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menufragment.MenuFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.LoginFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.gaz.GazPriceFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.news.NewsFragment
import com.codesroots.mac.cards.presentaion.menuitemsfragments.salary.SalaryFragment
import com.crashlytics.android.Crashlytics
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.onesignal.OneSignal
import com.romellfudi.ussdlibrary.USSDController
import io.fabric.sdk.android.Fabric
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import org.jetbrains.anko.telephonyManager
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    internal var timer = Timer()
    var android_id:String? = null
    lateinit var viewModel: MainViewModel
    lateinit var homeFragment: mainFragment
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.context = this
        binding.listener = ClickHandler()
        PreferenceHelper(this)

         android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

           OneSignal.initWithContext(this)
           OneSignal.setAppId("da675e7e-7eb3-48c7-8af1-ced224d0b3d0");
        OneSignal.sendTag("devicekey",android_id )

        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)

        homeFragment = mainFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        // Create messages
        Fabric.with(this, Crashlytics())
        //Crashlytics.getInstance().crash() // Force a crash

        if (PreferenceHelper.getUserId() == 0){

    val fragment = LoginFragment()
    val mFragmentTransaction = supportFragmentManager.beginTransaction()
    mFragmentTransaction.replace(R.id.main_frame, fragment)
    mFragmentTransaction.addToBackStack(null)
    mFragmentTransaction.commit()

}else {

            val swipeTimer = Timer()
            swipeTimer.schedule(object : TimerTask() {
                override fun run() {
                    Check()
                }
            }, 1000, 20000)
        }


        viewModel.ValidationLD!!.observe(this, Observer {
            if (it == -1 ) {
                OneSignal.sendTag("id", "0")
                PreferenceHelper.ClearUserID()
                moveTaskToBack(true);
                exitProcess(-1)

            }

        })
    }


fun Check(){
    viewModel.GetValidation(android_id!!,PreferenceHelper.getUserId().toString())
}






}

