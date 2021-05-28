package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mccproject.R

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var register=findViewById<TextView>(R.id.register)
        register.setOnClickListener {
            var i = Intent(this,SignUp::class.java)
            startActivity(i)
        }
    }
}