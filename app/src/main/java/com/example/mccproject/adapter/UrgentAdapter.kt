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
import kotlinx.android.synthetic.main.itemnew.view.cardView
import kotlinx.android.synthetic.main.urgent_item.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class UrgentAdapter(var context: Context, articles: List<ArticlesModel>) :
    RecyclerView.Adapter<UrgentAdapter.ViewHolder>() {
    var articles: List<ArticlesModel> = articles
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // val view: View = LayoutInflater.from(parent.context).inflate(R.layout.itemm, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.urgent_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val a: ArticlesModel = articles[position]
        holder.tvTitle.text = a.title
        holder.tvSource.text = a.source!!.name
        holder.desc.text = a.description
        holder.tvDate.text = "\u2022" + dateTime(a.publishedAt)
        holder.cardView.setOnClickListener {
            val intent = Intent(context, DetailsApi::class.java)
            intent.putExtra("title", a.title)
            intent.putExtra("source", a.source!!.name)
            intent.putExtra("time", dateTime(a.publishedAt))
            intent.putExtra("imageUrl", a.urlToImage)
            intent.putExtra("url", a.url)
            intent.putExtra("desc", a.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

          var  tvTitle = itemView.tvTitle_urgent
          var   tvSource = itemView.tvSource_urgent
          var  tvDate = itemView.tvDate_urgent
          var  cardView = itemView.cardView_urgent
        var desc = itemView.tv_desc_urgent

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

}