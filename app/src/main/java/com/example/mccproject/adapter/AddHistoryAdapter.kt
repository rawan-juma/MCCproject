package com.example.mccproject.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mccproject.Activities.HistoriNewsDetails
import com.example.mccproject.R
import com.example.mccproject.model.HistoryModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_histori_news_details.*
import kotlinx.android.synthetic.main.history_item.view.*



class AddHistoryAdapter(var context:Context,var act: FragmentActivity, var data:MutableList<HistoryModel>)
    : RecyclerView.Adapter<AddHistoryAdapter.ViewHolder>() {
    var videoPath = ""
    var playerr: SimpleExoPlayer? =null
    private var playReady =true
    private  var currentWindow = 0
    private var playedPostion:Long = 0
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)  {
        val tvTitle = item.tvTitle
        val tvSourceHist = item.tvSourceHist
        val tvDateHist = item.tvDateHist
        val image = item.image
        val cardView = item.cardView
        val vedio = item.video_view_rc

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(act).inflate(R.layout.history_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.tvTitle.text = data[position].title
        holder.tvSourceHist.text = data[position].author
        holder.tvDateHist.text = data[position].date
        var type = data[position].type
        if(type == "1"){

                playerr= ExoPlayerFactory.newSimpleInstance(context)
                holder.vedio.player =playerr!!
                var uri = Uri.parse(data[position].image)
                var dataSource = DefaultDataSourceFactory(context,"exoplayer-codelab")
                var mediaSource : MediaSource = ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri)
                playerr!!.playWhenReady = playReady
                playerr!!.seekTo(currentWindow,playedPostion)
                playerr!!.prepare(mediaSource,false,false)
holder.image.visibility = View.GONE
            holder.vedio.visibility = View.VISIBLE

        }else{
            Picasso.with(context).load(data[position].image).into(holder.image)
            holder.image.visibility = View.VISIBLE
            holder.vedio.visibility = View.GONE
        }


        holder.cardView.setOnClickListener {
            var i = Intent(context, HistoriNewsDetails::class.java)
            i.putExtra("image", data[position].image)
            i.putExtra("title", data[position].title)
            i.putExtra("author", data[position].author)
            i.putExtra("date", data[position].date)
            i.putExtra("description", data[position].description)
            i.putExtra("type",type)
            context.startActivity(i)
        }
    }


    interface onClick {
        fun onClickItem(position: Int)
    }




}