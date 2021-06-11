package com.example.mccproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
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
        btn_save_pie_chart.setOnClickListener {
            static()
            spinnerStatistics()

            supportFragmentManager.beginTransaction().replace(R.id.mainContainer, StatisticsFragment()).commit()

        }

        btn_save_pie_chart.setOnClickListener {
            //  var i = Intent(this,MainActivity::class.java)
            //   startActivity(i)
        }


    }

    private  fun static(){
        val statictNum = et_nums_pie_chart.text.toString()
        val typestatic = sp_title_statices_pie.selectedItem.toString()
        val fb = FirebaseFirestore.getInstance()

        val statics: MutableMap<String, Any> = HashMap()
        statics["statictNum"] = statictNum
        statics["typestatic"] = typestatic

        fb.collection("statistics")
                // add items
                .add(statics)
                .addOnSuccessListener {
                    Toast.makeText(this, "Add success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error adding", Toast.LENGTH_LONG).show()

                }
    }



        private fun spinnerStatistics() {
        val statistics: MutableList<String> = ArrayList()

        fb.collection("type")
            .get()
            .addOnCompleteListener { querySnapshot ->
                if (querySnapshot.isSuccessful) {
                    for (document in querySnapshot.result!!) {
                        val data = document.data
                        val typestatic = data["typestatic"] as String?

                        statistics.add(typestatic!!)

                    }
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statistics)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sp_title_statices_pie.adapter = adapter

            }
    }
}