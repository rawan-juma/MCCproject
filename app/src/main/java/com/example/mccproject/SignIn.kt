package com.example.mccproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

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