package com.codesroots.mac.cards.presentaion

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.telephony.TelephonyManager.*
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.databinding.library.baseAdapters.BR.context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.bumptech.glide.Glide

import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.codesroots.mac.cards.DataLayer.helper.MyService
import com.codesroots.mac.cards.DataLayer.helper.PhoneCallListener
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.DataLayer.helper.USSDService
import com.codesroots.mac.cards.R

import com.codesroots.mac.cards.models.Buypackge

import com.codesroots.mac.cards.presentaion.companydetails.fragment.CompanyDetails
import com.codesroots.mac.cards.presentaion.mainfragment.mainFragment
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.menufragmen.MenuFragment
import com.codesroots.mac.cards.presentaion.payment.Payment
import com.codesroots.mac.cards.presentaion.reportsFragment.ReportsFragment
import com.crashlytics.android.Crashlytics
import com.romellfudi.ussdlibrary.USSDApi
import com.romellfudi.ussdlibrary.USSDController
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_add_reserve.view.*
import kotlinx.android.synthetic.main.company_details_item.*
import kotlinx.android.synthetic.main.terms_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.telephonyManager

import java.io.IOException
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


class MainActivity : AppCompatActivity()  {





    lateinit var viewModel: MainViewModel
    private var map: HashMap<String, HashSet<String>>? = null

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun makePhonecall(id:Long, phone: String) {

        viewModel =   ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.EditOrder(id)

        if (viewModel.EditResponseLD?.hasObservers() == false) {
            viewModel.EditResponseLD?.observe(this, Observer {
                    if (it.success == true) {
//                var phoneListener = PhoneCallListener()
//                var mTelephonyManager = getSystemService(TELEPHONY_SERVICE);
//
//                this.getSystemService(Context.TELEPHONY_SERVICE)
//                telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE)
//
//                val intent = Intent(Intent.ACTION_CALL)
//                intent.data = Uri.fromParts("tel", phone, "#")
//                if (ActivityCompat.checkSelfPermission(
//                        this,
//                        Manifest.permission.CALL_PHONE
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    ActivityCompat.requestPermissions(
//                        this,
//                        arrayOf(Manifest.permission.CALL_PHONE),
//                        15
//                    )
//
//                }
//                startActivity(intent)
//           }
                map = HashMap()
                map!!["KEY_LOGIN"] = HashSet(Arrays.asList("espere", "waiting", "loading", "esperando"))
                map!!["KEY_ERROR"] = HashSet(Arrays.asList("problema", "problem", "error", "null"))
                        intent.data = Uri.fromParts("tel", phone, "#")

                var ussdApi = USSDController.getInstance(this)
                ussdApi.callUSSDOverlayInvoke(phone, map!!, object : USSDController.CallbackInvoke {
                    override fun responseInvoke(message: String) {
                        // message has the response string data
                        print(message)
                        Toast.makeText(this@MainActivity,message, Toast.LENGTH_SHORT).show()

                        var dataToSend = "data"// <- send "data" into USSD's input text
                        ussdApi!!.send("1") {
                            // it: response String
                            print(it)
                            if (it == "0مزيد") {

                                ussdApi!!.cancel()
                            }
                            Toast.makeText(this@MainActivity,it, Toast.LENGTH_SHORT).show()

                            // message has the response string data from USSD
                        }
                    }

                    override fun over(message: String) {
                        // message has the response string data from USSD or error
                        // response no have input text, NOT SEND ANY DATA
                        print(message)
                        Toast.makeText(this@MainActivity,message, Toast.LENGTH_SHORT).show()

                    }
                })
                }
            })
    }
    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        //////// when new order come
        val new_order = intent.getIntExtra("new_order", 0)
        if (new_order == 1) {
            var phone = intent.getStringExtra("phone")
            var code = intent.getStringExtra("code")
            var price = intent.getStringExtra("price")
            var id = intent.getLongExtra("id",0)

            makePhonecall(id,code+price+"*"+phone+"#")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PreferenceHelper(this)
   //startService(Intent(this, USSDService::class.java))
                        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_PHONE_STATE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE),
                        15
                    )}
//


        val typeface = ResourcesCompat.getFont(this, R.font.fonts)


        // Create messages
        Fabric.with(this,  Crashlytics())
        //Crashlytics.getInstance().crash() // Force a crash

        val item1 = AHBottomNavigationItem(
            R.string.tab_1,
            R.drawable.house_outline, R.color.signinpurple

        )
        val item2 = AHBottomNavigationItem(
            R.string.tab_2,
            R.drawable.analytics, R.color.signinpurple
        )
        val item3 = AHBottomNavigationItem(
            R.string.tab_3,
            R.drawable.more, R.color.signinpurple
        )


        with(bottom_navigation) {
            addItems(listOf(item2, item1,item3))

            inactiveColor = ContextCompat.getColor(context ,R.color.gray )
            accentColor  =  ContextCompat.getColor(context ,R.color.signinpurple )

            currentItem = 1

            setOnTabSelectedListener { position, wasSelected ->Unit
                Log.e( "tab positiion",position.toString())
                /*  getLastLocation()*/

                if (position==2) {
                    supportFragmentManager!!.beginTransaction()
                        .replace(com.codesroots.mac.cards.R.id.main_frame, MenuFragment()).addToBackStack(null).commit()
                }
                if (position==1) {
                    supportFragmentManager!!.beginTransaction()
                        .replace(R.id.main_frame, mainFragment()).addToBackStack(null).commit()
                }
                if (position == 0){
                    supportFragmentManager!!.beginTransaction()
                        .replace(R.id.main_frame, ReportsFragment()).addToBackStack(null).commit()


                }
                true

            }
            supportFragmentManager.beginTransaction().replace(R.id.main_frame, mainFragment() , "Main").addToBackStack(null).commit()

        }
        if (PreferenceHelper.getUserId() == 1 ) {
            startService(Intent(this, MyService::class.java))
         //   startService(Intent(this, USSDService::class.java))

        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //询问用户权限
        if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0] == Manifest.permission.READ_PHONE_STATE) {
            //用户同意
        } else {
            //用户不同意
        }
    }
}



class ClickHandler {
    var  mLastClickTime: Long = 0
     fun makePhonecall(context: Context,phone: String) {

         val intent = Intent(Intent.ACTION_CALL)
         intent.data = Uri.parse("tel:$phone")
         if (ActivityCompat.checkSelfPermission(
                 context,
                 Manifest.permission.CALL_PHONE
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             ActivityCompat.requestPermissions(
                 context as Activity,
                 arrayOf(Manifest.permission.CALL_PHONE),
                 15
             )
             return
         }
         context.startActivity(intent)



    }
    fun SwitchToPackages( context: Context,comid :String) {



        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = CompanyDetails()
        frag.arguments =bundle
        bundle.putString("packageId" , comid)
        ( context as MainActivity).supportFragmentManager!!.beginTransaction()
            .replace(R.id.main_frame, frag).addToBackStack(null).commit()
    }
    fun SwitchToReports( context: Context,comid :String) {

        val bundle = Bundle()
        //  bundle.putParcelable("cliObj" ,clients[position] )
        val frag = ReportsFragment()
        frag.arguments =bundle
        bundle.putString("packageId" , comid)
        ( context as MainActivity).supportFragmentManager!!.beginTransaction()
            .replace(com.codesroots.mac.cards.R.id.main_frame, frag).addToBackStack(null).commit()
    }

    fun SwitchToPayment(context: Context,id:String,viewmodel:MainViewModel) {

        val dialogBuilder = AlertDialog.Builder(( context as MainActivity) )
        val inflater = ( context as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.alert_add_reserve, null)

        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        var  title =  TextView(context as MainActivity);
// You Can Customise your Title here
        title.setText("إضافة طلب");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20f);

        dialogBuilder.setCustomTitle(title);
        alertDialog.show()
        dialogView.save.setOnClickListener { v: View? ->
            if (SystemClock.elapsedRealtime() - mLastClickTime < 10000){
                return@setOnClickListener
            }
            v!!.isGone = true

            mLastClickTime = SystemClock.elapsedRealtime();
            val auth = PreferenceHelper.getToken()
            viewmodel.BuyPackage(id,dialogView.from.text.toString())

            if (viewmodel.BuyPackageResponseLD?.hasObservers() == false) {
                viewmodel.BuyPackageResponseLD?.observe(context, Observer {


                    if (it.center!!.err != null) {
                        it.center!!.err!!.snack((context as MainActivity).window.decorView.rootView)
                        dialogView.err.text = it.center!!.err
                        dialogView.err.isGone = false
                    } else {

                        if (it!!.center!!.id != null) {
                            val homeIntent = Intent(context, Payment::class.java)


                            (context as MainActivity).startActivity(homeIntent)


                        }

                    }

                })
            }
        }
    }




}
