package com.example.mccproject.Activities

import android.accounts.AccountManager.get
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import com.example.mccproject.R
import com.example.mccproject.model.HistoryModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_histori_news_details.*
import kotlinx.android.synthetic.main.history_item.*
import java.nio.file.Paths.get

class HistoriNewsDetails : AppCompatActivity() {
    private  var FontSize = 14f
    var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histori_news_details)
        db = Firebase.firestore
        color.setOnClickListener {
            tvTitleHistori.text
            tvTitleHistori.setTextSize(18f)
            tvTitleHistori.setBackgroundColor(Color.parseColor("#FF018786"))
        }

        var x= intent.getStringExtra("image")
        image(HistoryModel("","","","","",x))
        var z=intent.getStringExtra("title")
        tvTitleHistori.text=z
        var v=intent.getStringExtra("description")
        tvDescHistori.text=v
        var m=intent.getStringExtra("author")
        tvSourceHistori.text=m
        var d=intent.getStringExtra("date")
        tvDateHistori.text=d

    }

    fun image(data: HistoryModel){
        Picasso.with(this).load(data.image).into(imageViewHistori)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.con_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.increas -> {
                FontSize +=4f
                tvDescHistori.setTextSize(TypedValue.COMPLEX_UNIT_SP,FontSize)
            }

            R.id.decres -> {
                FontSize -=4f
                tvDescHistori.setTextSize(TypedValue.COMPLEX_UNIT_SP,FontSize)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}