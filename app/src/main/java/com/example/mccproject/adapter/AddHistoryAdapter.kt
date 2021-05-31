package com.example.mccproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mccproject.R
import com.example.mccproject.model.HistoryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.history_item.view.*
import kotlinx.android.synthetic.main.itemnew.view.cardView
import kotlinx.android.synthetic.main.itemnew.view.image
import kotlinx.android.synthetic.main.itemnew.view.tvDate
import kotlinx.android.synthetic.main.itemnew.view.tvTitle

class AddHistoryAdapter(var act: FragmentActivity, var data:MutableList<HistoryModel>)
    : RecyclerView.Adapter<AddHistoryAdapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)  {
        val tvTitle = item.tvTitle
        val image = item.image
        val tvAuthor = item.tvAuthor
        val tvDate = item.tvDate
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
        holder.tvAuthor.text = data[position].author
        holder.tvDate.text = data[position].date
        Picasso.get().load(data[position].image).into(holder.image)

    }


    interface onClick {
        fun onClickItem(position: Int)
    }



}