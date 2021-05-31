package com.example.mccproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_histori_news_details.*

class HistoriNewsDetails : AppCompatActivity() {
    private  var FontSize = 14f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histori_news_details)

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