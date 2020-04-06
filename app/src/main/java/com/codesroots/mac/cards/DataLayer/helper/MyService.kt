package com.codesroots.mac.cards.DataLayer.helper


import android.annotation.SuppressLint
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.codesroots.mac.cards.DataLayer.ApiService.ApiClient
import com.codesroots.mac.cards.models.ReportDaily

import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import com.codesroots.mac.cards.presentaion.toast
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.util.Timer
import java.util.TimerTask


class MyService : Service(), NetworkChangeReceiver.ConnectivityReceiverListener {
    internal var mReceiver: BroadcastReceiver? = null
    internal var isRegistered = false
    internal var userKey: String? = null
    internal var timer = Timer()
    lateinit var viewModel: MainViewModel
    var ReportHistroyResponseLD : MutableLiveData<ReportDaily>? = null

    override fun onCreate() {
        // get an instance of the receiver in your service
        val filter = IntentFilter()
        filter.addAction("action")
        filter.addAction("anotherAction")
        mReceiver = MyReceiver()
        //        MyApplication.getInstance().setConnectivityListener(this);
        registerReceiver(mReceiver, filter)


        timer.scheduleAtFixedRate(
            object : TimerTask() {
                @SuppressLint("CheckResult")
                override fun run() {

                    getServergetway().GetCenterOrder()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { data -> data }
                        .subscribe(
                            { books ->
                                if (books.myorders!!.id != null) {
                                    val intent1 = Intent(applicationContext, MainActivity::class.java)
                                      //  toast.ma(this@MyService,"aa")
                                    Toast.makeText(this@MyService,"طلب جديد", Toast.LENGTH_SHORT).show()
                                    intent1.putExtra("new_order",1)
                                    intent1.putExtra("id",books.myorders.id)
                                    intent1.putExtra("price",books.myorders.productPackage.price)
                                    intent1.putExtra("code",books.myorders.productPackage.company!!.code)
                                    intent1.putExtra("phone",books.myorders.mobile)


                                    intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                                    startActivity(intent1)
                                }else {
                                    Toast.makeText(this@MyService,"خطا", Toast.LENGTH_SHORT).show()

                                }
                            },
                            { error ->

                            }
                        )

                }
            },
            0, 30000
        )


    }


    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {}

    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // do something

        }
    }
    fun getServergetway () : APIServices
    {
        return ApiClient.getClient().create(APIServices::class.java)
    }
    override fun onDestroy() {
        super.onDestroy()
        if (mReceiver != null)
        //  if (isRegistered) {
            unregisterReceiver(mReceiver)

        //   }
    }

}