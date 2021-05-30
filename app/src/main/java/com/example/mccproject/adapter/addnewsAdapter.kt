package com.example.mccproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mccproject.R
import com.example.mccproject.model.addnewsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.itemnew.view.*

class addnewsAdapter (var act: FragmentActivity, var data:MutableList<addnewsModel>)
    : RecyclerView.Adapter<addnewsAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)  {
        val tvTitle = item.tvTitle
        val image = item.image
        val tvAuthor = item.tvAuthor
        val tvDate = item.tvDate
        val cardView = item.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(act).inflate(R.layout.itemnew, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.tvTitle.text = data[position].title
        holder.tvAuthor.text = data[position].author
        holder.tvDate.text = data[position].date
        Picasso.get().load(data[position].image).into(holder.image)

    }


    interface onClick {
        fun onClickItem(position: Int)
    }

}