package com.example.mccproject.Activities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    lateinit var charSequence : CharSequence
     var index : Int=0
    var delay :Long = 200
var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var objAnimator = ObjectAnimator.ofPropertyValuesHolder(
            iv_top, PropertyValuesHolder.ofFloat(
                "scaleX",
                1.2f
            ),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        objAnimator.duration = 500
        objAnimator.repeatCount = ValueAnimator.INFINITE
        objAnimator.repeatMode = ValueAnimator.REVERSE
        objAnimator.start()

    animatText("نبض القدس")

 Handler().postDelayed(Runnable {
     startActivity(Intent(this,SignIn::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
     finish()
 },4000)
    }

    var runnable: Runnable = object : Runnable {
        override fun run() {
             text_view.text = charSequence.subSequence(0,index++)
            if (index <= charSequence.length) {
                handler.postDelayed(this, delay)
            }
        }
    }

    fun animatText(cs : CharSequence){
        charSequence = cs
        index = 0
        text_view.text = ""
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable,delay)
    }
}