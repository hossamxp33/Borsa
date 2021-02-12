package com.codesroots.mac.cards.DataLayer.helper;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.onesignal.OneSignal;


public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.DEBUG);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       MultiDex.install(this);
    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }



//    public RequestQueue getRequestQueue() {
//        if (mRequestQueue == null) {
//            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        }
//
//        return mRequestQueue;
//    }
//
    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }
}

