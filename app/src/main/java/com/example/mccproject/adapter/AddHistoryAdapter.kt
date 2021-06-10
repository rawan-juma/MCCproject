package com.example.mccproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mccproject.Activities.HistoriNewsDetails
import com.example.mccproject.R
import com.example.mccproject.model.HistoryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.history_item.view.*



class AddHistoryAdapter(var context:Context,var act: FragmentActivity, var data:MutableList<HistoryModel>)
    : RecyclerView.Adapter<AddHistoryAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)  {
        val tvTitle = item.tvTitle
        val tvSourceHist = item.tvSourceHist
        val tvDateHist = item.tvDateHist
        val image = item.image
        val cardView = item.cardView

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
        Picasso.with(act).load(data[position].image).into(holder.image)
        holder.tvSourceHist.text = data[position].author
        holder.tvDateHist.text = data[position].date
        Picasso.with(context).load(data[position].image).into(holder.image)
        holder.cardView.setOnClickListener {
            var i = Intent(context, HistoriNewsDetails::class.java)
            i.putExtra("image", data[position].image)
            i.putExtra("title", data[position].title)
            i.putExtra("author", data[position].author)
            i.putExtra("date", data[position].date)
            i.putExtra("description", data[position].description)
            context.startActivity(i)
        }
    }


    interface onClick {
        fun onClickItem(position: Int)
    }



}