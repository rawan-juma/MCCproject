package com.example.mccproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import com.example.mccproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_api.*

class DetailsApi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_api)



        webViewLoader.visibility = View.VISIBLE

        val intent = intent
        val title = intent.getStringExtra("title")
        val source = intent.getStringExtra("source")
        val time = intent.getStringExtra("time")
        val desc = intent.getStringExtra("desc")
        val imageUrl = intent.getStringExtra("imageUrl")
        val url = intent.getStringExtra("url")


        tvTitle.text = title
        tvSource.text = source
        tvDate.text = time
        tvDesc.text = desc

        Picasso.with(this).load(imageUrl).into(imageView)

        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url!!)
        if (webView.isShown) {
            loader.visibility = View.INVISIBLE
        }
    }
}