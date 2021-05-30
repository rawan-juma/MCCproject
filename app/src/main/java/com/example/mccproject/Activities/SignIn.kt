package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var register=findViewById<TextView>(R.id.register)
        var signIn = findViewById<TextView>(R.id.signin)


        val sharedprf= getSharedPreferences("shared", MODE_PRIVATE)
//        val islogged=sharedprf.getBoolean("islogged",false)
        var email1= sharedprf.getString("email","No Data")
        edEmail.setText("$email1")
        var pass=sharedprf.getString("passSignUp","No Data")
        edPassword.setText("$pass")

        signIn.setOnClickListener {
            var i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        register.setOnClickListener {
            var i = Intent(this,SignUp::class.java)
            startActivity(i)
        }
    }
}