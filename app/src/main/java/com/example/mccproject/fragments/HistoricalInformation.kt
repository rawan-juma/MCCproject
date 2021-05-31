package com.example.mccproject.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mccproject.Activities.HistoriNewsDetails
import com.example.mccproject.R
import com.example.mccproject.adapter.AddHistoryAdapter
import com.example.mccproject.model.HistoryModel

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_form_statistics.*
import kotlinx.android.synthetic.main.fragment_historical_information.*
import kotlinx.android.synthetic.main.itemnew.view.*


class HistoricalInformation : Fragment() {

    var db: FirebaseFirestore? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_historical_information, container, false)

        db = Firebase.firestore
        getAllNews()
//        root.flatAddNew.setOnClickListener {
//            var i = Intent(context, AddNews::class.java)
//            startActivity(i)
//        }

        return  root
    }

    fun getAllNews() {
        val news = mutableListOf<HistoryModel>()
        db!!.collection("News").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val title = data["title"] as String
                        val author = data["author"] as String
                        val date = data["date"] as String
                        val image = data["image"] as String
                        val description = data["description"] as String
                        news.add(HistoryModel(id, title,author,date,description,image))
                    }
                    recycleVi.layoutManager = LinearLayoutManager(activity!!)
                    recycleVi.setHasFixedSize(true)
                    val HistoryAdapter = AddHistoryAdapter(activity!!,activity!!, news)
                    recycleVi.adapter = HistoryAdapter

                }
            }
    }

}