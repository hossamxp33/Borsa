package com.codesroots.mac.cards.presentaion.payment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.databinding.ActivityPaymentBinding
import com.codesroots.mac.cards.models.Buypackge
import com.codesroots.mac.cards.presentaion.MainActivity

import kotlinx.android.synthetic.main.activity_payment.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class Payment : AppCompatActivity() {


    var dataa: Buypackge? = null

    var  text : TextView ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val binding = DataBindingUtil.setContentView<ActivityPaymentBinding>(this, R.layout.activity_payment)


text = findViewById(R.id.textView2)

        animation()

        home.setOnClickListener {
                    val homeIntent = Intent(this, MainActivity::class.java)
                                       startActivity(homeIntent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun animation(){
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
         text!!.animation = ttb

    }
}