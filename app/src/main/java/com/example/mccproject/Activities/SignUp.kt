package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mccproject.R

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var login=findViewById<TextView>(R.id.login)
        login.setOnClickListener {
            var i = Intent(this,SignIn::class.java)
            startActivity(i)
        }

    }
}