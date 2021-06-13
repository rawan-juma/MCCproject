package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mccproject.R
import com.example.mccproject.DB.RegisterDBhelper
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var login=findViewById<TextView>(R.id.login)
        val db = RegisterDBhelper(this)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        signup.setOnClickListener {

                if(edUsername.text.isNotEmpty() &&  edEmail.text.isNotEmpty() &&  edPassword.text.isNotEmpty() &&  edConfirmPass.text.isNotEmpty() && (edPassword.text.toString() ==  edConfirmPass.text.toString())) {
                    if (db.insertUser(edUsername.text.toString(),edEmail.text.toString(),edPassword.text.toString())) {

                    Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()


                        val sharedprf= getSharedPreferences("sharedd", MODE_PRIVATE)
                        val editor=sharedprf.edit()
                        editor.putString("username",edUsername.text.toString())
                        editor.putString("email",edEmail.text.toString())
                        editor.putString("passSignUp",edPassword.text.toString())
                        editor.commit()
                        editor.apply()

                            var i = Intent(this,SignIn::class.java)
                            startActivity(i)

                    edUsername.text.clear()
                    edEmail.text.clear()
                    edPassword.text.clear()
                    edConfirmPass.text.clear()

                }else{
                    Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()

                }
                }else{
                    when {
                        edUsername.text.toString() == "" -> {
                            errFill(edUsername,"please fill username")
                        }
                        edEmail.text.toString() == "" -> {
                            errFill(edEmail,"please fill Email")
                        }
                        edPassword.text.toString() == "" -> {
                            errFill(edPassword,"please fill password")
                        }
                    }
                    Toast.makeText(this, "Fill Fields", Toast.LENGTH_SHORT).show()

                }


                /* val f=ProfileFragment()
                 val bundle=Bundle()
                 bundle.putInt("image",R.id.imageAddProduct)
                 bundle.putString("nameproduct",R.id.namepro.toString())
                 bundle.putString("companyname",R.id.nametajer.toString())
                 bundle.putString("type",R.id.spinnertype.toString())
                 bundle.putInt("price",R.id.price)
                 bundle.putString("description",R.id.description.toString())
                 f.arguments=bundle
                 val prof= Intent(this.context, Main2Activity::class.java)
                 startActivity(prof)
     */

            }

        login.setOnClickListener {
            var i = Intent(this,SignIn::class.java)
            startActivity(i)
        }

    }

    fun errFill(et : EditText,textmsg : String){
        et.error=textmsg
        et.isFocusable=true
    }
}