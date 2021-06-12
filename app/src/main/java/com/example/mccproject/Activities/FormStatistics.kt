package com.example.mccproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import com.example.mccproject.fragments.StatisticsFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_form_statistics.*

class FormStatistics : AppCompatActivity() {
    val fb = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_statistics)
        supportActionBar!!.title="تعديل الإحصائيات"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
       // spinnerStatistics()

        btn_save_pie_chart.setOnClickListener {
            static()
            startActivity(Intent(this, MainActivity::class.java))

        }

        btn_save_bar_chart.setOnClickListener {
            static2()
            startActivity(Intent(this, MainActivity::class.java))

        }


    }

    private  fun static(){
        val statictNum = et_nums_pie_chart.text.toString()
        val typestatic = sp_title_statices_pie.selectedItem.toString()

        val statics: MutableMap<String, Any> = HashMap()
        statics["statictNum"] = statictNum
        statics["typestatic"] = typestatic

        fb.collection("statistics")
                .add(statics)
                .addOnSuccessListener {
                    Toast.makeText(this, "Add success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error adding", Toast.LENGTH_LONG).show()

                }
    }
    private  fun static2(){
        val statictNumC = et_nums_bar_chart.text.toString()
        val statictcolor = sp_title_statices_bar.selectedItem.toString()

        val statics: MutableMap<String, Any> = HashMap()
        statics["statictNumC"] = statictNumC
        statics["statictcolor"] = statictcolor

        //    statics["typestatic"] = typestatic

        fb.collection("statistics2")
            .add(statics)
            .addOnSuccessListener {
                Toast.makeText(this, "Add success", Toast.LENGTH_LONG).show()
                Log.e("*************", "///////////////+$statictNumC+$statictcolor")

            }
            .addOnFailureListener {
                Toast.makeText(this, "Error adding", Toast.LENGTH_LONG).show()

            }
    }


//        private fun spinnerStatistics() {
//        val statistics: MutableList<String> = ArrayList()
//
//            fb.collection("statistics")
//            .get()
//            .addOnCompleteListener { querySnapshot ->
//                if (querySnapshot.isSuccessful) {
//                    for (document in querySnapshot.result!!) {
//                        val data = document.data
//                        val typestatic = data["typestatic"] as String?
//                        statistics.add(typestatic!!)
//
//                    }
//                }
//                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statistics)
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                sp_title_statices_pie.adapter = adapter
//
//            }
//    }
}