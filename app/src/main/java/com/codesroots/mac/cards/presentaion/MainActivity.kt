package com.codesroots.mac.cards.presentaion

import android.Manifest
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
import com.google.android.gms.cast.CastRemoteDisplayLocalService.startService
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.romellfudi.ussdlibrary.USSDController
import io.fabric.sdk.android.Fabric

import org.jetbrains.anko.telephonyManager
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


class MainActivity : AppCompatActivity() {

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


        homeFragment = mainFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        // Create messages
        Fabric.with(this, Crashlytics())
        //Crashlytics.getInstance().crash() // Force a crash


    }









}

