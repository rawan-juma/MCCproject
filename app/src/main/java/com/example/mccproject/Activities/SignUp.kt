package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD:app/src/main/java/com/example/mccproject/SignUp.kt
import android.widget.TextView
=======
import com.example.mccproject.R
>>>>>>> 0251a33da570fa20ad0337b0bdae6b3e246dfcb8:app/src/main/java/com/example/mccproject/Activities/SignUp.kt

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