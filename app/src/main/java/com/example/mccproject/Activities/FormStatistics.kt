package com.example.mccproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_form_statistics.*

class FormStatistics : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_statistics)
        supportActionBar!!.title="تعديل الإحصائيات"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btn_save_pie_chart.setOnClickListener {
          //  var i = Intent(this,MainActivity::class.java)
         //   startActivity(i)
        }

        btn_save_pie_chart.setOnClickListener {
            //  var i = Intent(this,MainActivity::class.java)
            //   startActivity(i)
        }
    }
}