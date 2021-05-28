package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
<<<<<<< HEAD
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_sign_up.*
=======
import com.example.mccproject.R
>>>>>>> 980719cad022731a60a15ee239b4941e00bc31f9

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var login=findViewById<TextView>(R.id.login)

        signup.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            var i = Intent(this,SignIn::class.java)
            startActivity(i)
        }

    }
}