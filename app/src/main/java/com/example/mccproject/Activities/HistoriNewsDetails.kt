package com.example.mccproject.Activities

import android.accounts.AccountManager.get
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.mccproject.R
import com.example.mccproject.model.HistoryModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_api.*
import kotlinx.android.synthetic.main.activity_histori_news_details.*
import kotlinx.android.synthetic.main.history_item.*
import petrov.kristiyan.colorpicker.ColorPicker
import java.nio.file.Paths.get

class HistoriNewsDetails : AppCompatActivity() {
    private  var FontSize = 14f
    var db: FirebaseFirestore? = null
    var videoPath = ""
    var playerr: SimpleExoPlayer? =null
    private var playReady =true
    private  var currentWindow = 0
    private var playedPostion:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histori_news_details)
        db = Firebase.firestore
        color.setOnClickListener {
            val colorPicker = ColorPicker(this)
            colorPicker.show()
            colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    tvTitleHistori.setTextColor(color)
                    //  tvTitleHistori.setBackgroundColor(Color.parseColor("#FF018786"))
                }

                override fun onCancel() {
                    // put code
                }
            })
            tvTitleHistori.text
            tvTitleHistori.setTextSize(18f)

        }
        tvDescHistori.setOnClickListener {
            val colorPicker = ColorPicker(this)
            colorPicker.show()
            colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                   tvDescHistori.setTextColor(color)
                    //  tvTitleHistori.setBackgroundColor(Color.parseColor("#FF018786"))
                }

                override fun onCancel() {
                    // put code
                }
            })
        }
          var type = intent.getStringExtra("type")
        var x= intent.getStringExtra("image")

        var z=intent.getStringExtra("title")
        tvTitleHistori.text=z
        var v=intent.getStringExtra("description")
        tvDescHistori.text=v
        var m=intent.getStringExtra("author")
        tvSourceHistori.text=m
        var d=intent.getStringExtra("date")
        tvDateHistori.text=d


        if (type == "1"){
            imageViewHistori.visibility = View.GONE
            video_view_add.visibility = View.VISIBLE
            initVideo(HistoryModel("","","","","",x,type))
        }else if (type == "2"){
            image(HistoryModel("","","","","",x,type))
            video_view_add.visibility = View.GONE
            imageViewHistori.visibility = View.VISIBLE
        }


    }

    fun image(data: HistoryModel){
        Picasso.with(this).load(data.image).into(imageViewHistori)
    }
    fun initVideo(data: HistoryModel){
        playerr= ExoPlayerFactory.newSimpleInstance(this)
        video_view_add.player =playerr!!
        var uri = Uri.parse(data.image)
        var dataSource = DefaultDataSourceFactory(this,"exoplayer-codelab")
        var mediaSource : MediaSource = ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri)
        playerr!!.playWhenReady = playReady
        playerr!!.seekTo(currentWindow,playedPostion)
        playerr!!.prepare(mediaSource,false,false)

    }

    fun releaseVideo(){
        if (playerr != null){
            playReady = playerr!!.playWhenReady
            playedPostion = playerr!!.currentPosition
            currentWindow = playerr!!.currentWindowIndex
            playerr!!.release()
            playerr = null
        }
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

    override fun onPause() {
        super.onPause()
        releaseVideo()
    }
}