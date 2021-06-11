package com.example.mccproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mccproject.Activities.HistoriNewsDetails
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import com.example.mccproject.fragments.HistoricalInformation
import com.example.mccproject.model.HistoryModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_histori_news_details.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.history_item.view.*
import kotlinx.android.synthetic.main.update_news.view.*


class AddHistoryAdapter(var context:Context,var act: FragmentActivity, var data:MutableList<HistoryModel>)
    : RecyclerView.Adapter<AddHistoryAdapter.ViewHolder>() {

    val db= FirebaseFirestore.getInstance()

    var videoPath = ""

    var playerr: SimpleExoPlayer? =null
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)  {
        val tvTitle = item.tvTitle
        val tvSourceHist = item.tvSourceHist
        val tvDateHist = item.tvDateHist
        val image = item.image
        val cardView = item.cardView
        val vedio = item.video_view_rc
        val update = item.updateBtn
        val delete = item.deleteBtn
        val txt = item.txt


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
//        holder.tvAuthor.text = data[position].author
//        holder.tvDate.text = data[position].date
        val sharedPreferences= context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        var email=sharedPreferences.getString("email","")
        holder.txt.text = email
        Picasso.with(act).load(data[position].image).into(holder.image)
        holder.tvSourceHist.text = data[position].author
        holder.tvDateHist.text = data[position].date
        var type = data[position].type
        if(type == "1"){

                playerr= ExoPlayerFactory.newSimpleInstance(context)
                holder.vedio.player =playerr!!
                var uri = Uri.parse(data[position].image)
                var dataSource = DefaultDataSourceFactory(context,"exoplayer-codelab")
                var mediaSource : MediaSource = ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri)

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
        if(email.equals("admin@gmail.com")){
            var play = holder.update
            play.isClickable=false
            play.visibility=View.VISIBLE
            var play1 = holder.delete
            play1.isClickable=false
            play1.visibility=View.VISIBLE
        }else{
            var play = holder.update
            play.isClickable=false
            play.visibility=View.INVISIBLE
            var play1 = holder.delete
            play1.isClickable=false
            play1.visibility=View.INVISIBLE
        }
        holder.update.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(context)
            var view = LayoutInflater.from(context).inflate(R.layout.update_news, null)
            val alertDialog = alertBuilder.create()
            alertDialog.setView(view)
            alertDialog.show()
            view.editTextTitle.setText(data[position].title)
            view.editTextDate.setText(data[position].date)
            view.editTextAuthor.setText(data[position].author)
            view.editTextTextDescription.setText(data[position].description)
            view.update.setOnClickListener {
                updateNew(data[position].id,view.editTextTitle.text.toString(),view.editTextAuthor.text.toString(),view.editTextDate.text.toString(),view.editTextTextDescription.text.toString())
                val i=Intent(context, MainActivity::class.java)
                context!!.startActivity(i)
            }

        }
        holder.delete.setOnClickListener {
            deleteNew(data[position].id)
            Toast.makeText(context,"${data[position].id}", Toast.LENGTH_SHORT).show()
            val i=Intent(context, MainActivity::class.java)
            context!!.startActivity(i)

        }
    }

    private fun updateNew(id:String,title: String,author: String,date: String,description:String){
        val News=HashMap<String,Any>()
        News["title"]=title
        News["date"]=date
        News["author"]=author
        News["description"]=description
        db.collection("News").document(id)
            .update(News)
            .addOnSuccessListener {
                Toast.makeText(context,"successful.......", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener{
                Toast.makeText(context,"fail........", Toast.LENGTH_SHORT).show()
            }


    }
    private fun deleteNew(id:String){
        db.collection("News").document(id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(context,"successful.......", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context,"fail......", Toast.LENGTH_SHORT).show()
            }
    }








}