package com.example.mccproject.Activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_add_news.*
import java.util.*

class AddNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)
        supportActionBar!!.title="إضافة خبر"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // design Date picker "just"
        editTextDate.setOnClickListener {
            datePickerDialog()
        }
    }

    fun datePickerDialog(){
        val cldr = Calendar.getInstance()
        val day = cldr.get(Calendar.DAY_OF_MONTH)
        val month = cldr.get(Calendar.MONTH)
        val year = cldr.get(Calendar.YEAR)
// date picker dialog
        val picker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                editTextDate.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
            }, year, month, day
        )
        picker.show()
    }
}