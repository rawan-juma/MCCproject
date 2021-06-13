package com.example.mccproject.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.edEmail
import kotlinx.android.synthetic.main.activity_sign_in.edPassword
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.UnsupportedEncodingException


class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var register=findViewById<TextView>(R.id.register)
        var signIn = findViewById<TextView>(R.id.signin)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedprf= getSharedPreferences("sharedd", MODE_PRIVATE)
//        val islogged=sharedprf.getBoolean("islogged",false)
        var email1= sharedprf.getString("email", "")
        edEmail.setText("$email1")
        var pass=sharedprf.getString("passSignUp", "")
        edPassword.setText("$pass")
        var username=sharedprf.getString("username", "")
        signIn.setOnClickListener {
            if(edEmail.text.toString() == "admin@gmail.com" && edPassword.text.toString() == "123456"){
                val sharedprf= getSharedPreferences("shared", MODE_PRIVATE)
                val editor=sharedprf.edit()
                editor.putString("username", "Admin")
                editor.putString("email", edEmail.text.toString())
                editor.putString("passSignIn", edPassword.text.toString())
                editor.commit()
                editor.apply()
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)}
            else if (edEmail.text.toString() == email1 && edPassword.text.toString() == pass){
                Toast.makeText(this, "No admin", Toast.LENGTH_SHORT).show()
                val sharedprf= getSharedPreferences("shared", MODE_PRIVATE)
                val editor=sharedprf.edit()
                editor.putString("username", username)
                editor.putString("email", edEmail.text.toString())
                editor.putString("passSignIn", edPassword.text.toString())
                editor.commit()
                editor.apply()
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)

            }
            getRegToken(edEmail.text.toString(), edPassword.text.toString())
        }
        register.setOnClickListener {

            var i = Intent(this, SignUp::class.java)
            startActivity(i)
        }
    }



    private fun getRegToken(emaill: String, pass: String) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("dna", "Failed to get token ${task.exception}")

                return@addOnCompleteListener
            }
            val token = task.result
            val data = "{" +

                    "\"email\"" + ":" + "\"" + emaill + "\"," +
                    "\"password\"" + ":" + "\"" + pass + "\"" +
                    "\"reg_token\"" + ":" + "\"" + token!! + "\"," +
                    "}"

               try {
                                Log.d("quds", "savedata: $data")
                                if (data == null) null else data.toByteArray(charset("utf-8"))
                            } catch (uee: UnsupportedEncodingException) {
                                null
                            }!!
                        }



        }
    }
