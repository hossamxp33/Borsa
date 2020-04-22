package com.codesroots.mac.cards.presentaion.splash

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.codesroots.mac.cards.DataLayer.helper.MyApplication.context
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashScreen : AppCompatActivity() {

    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 1100 //splash screen will be shown for 2 seconds

    /** Called when the activity is first created.  */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}