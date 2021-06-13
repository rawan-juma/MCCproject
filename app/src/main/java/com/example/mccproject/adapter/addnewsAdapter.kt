package com.example.mccproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mccproject.Activities.DetailsApi
import com.example.mccproject.R
import com.example.myapplication.ArticlesModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.itemnew.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class addnewsAdapter(var context: Context, articles: List<ArticlesModel>) :
        RecyclerView.Adapter<addnewsAdapter.ViewHolder>() {
    var articles: List<ArticlesModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // val view: View = LayoutInflater.from(parent.context).inflate(R.layout.itemm, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemnew, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val a: ArticlesModel = articles[position]
        val imageUrl: String? = a.urlToImage
        Picasso.with(context).load(imageUrl).into(holder.imageView)
        holder.tvTitle.text = a.title
        holder.tvDate.text = "\u2022" + dateTime(a.publishedAt)
        holder.cardView.setOnClickListener {
            val intent = Intent(context, DetailsApi::class.java)
            intent.putExtra("title", a.title)
            intent.putExtra("source", a.source!!.name)
            intent.putExtra("time", dateTime(a.publishedAt))
            intent.putExtra("desc", a.description)
            intent.putExtra("imageUrl", a.urlToImage)
            intent.putExtra("url", a.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvDate: TextView
        var imageView: ImageView
        var cardView: CardView

        init {
            tvTitle = itemView.tvTitle
            tvDate = itemView.tvDate
            imageView = itemView.image
            cardView = itemView.cardView
        }
    }

    fun dateTime(t: String?): String? {
        val prettyTime = PrettyTime(Locale(country))
        var time: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.ENGLISH)
            val date = simpleDateFormat.parse(t)
            time = prettyTime.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return time
    }

    val country: String
        get() {
            val locale = Locale.getDefault()
            val country = locale.country
            return country.toLowerCase()
        }

    init {
        this.articles = articles
    }
}