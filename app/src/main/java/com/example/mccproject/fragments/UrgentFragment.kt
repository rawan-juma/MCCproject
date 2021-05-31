package com.example.mccproject.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mccproject.Activities.AddNews
import com.example.mccproject.R
import com.example.mccproject.adapter.addnewsAdapter
import com.example.mccproject.model.addnewsModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_urgent.*
import kotlinx.android.synthetic.main.fragment_urgent.view.*

/**
 * A simple [Fragment] subclass.
 */
class UrgentFragment : Fragment(){
    var db: FirebaseFirestore? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_urgent, container, false)
        db = Firebase.firestore
//        getAllNews()
        root.flatAddNew.setOnClickListener {
            var i = Intent(context, AddNews::class.java)
            startActivity(i)
        }
        return  root
    }

//    fun getAllNews() {
//        val news = mutableListOf<addnewsModel>()
//        db!!.collection("News").get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    for (document in task.result!!) {
//                        val id = document.id
//                        val data = document.data
//                        val title = data["title"] as String
//                        val author = data["author"] as String
//                        val date = data["date"] as String
//                        val image = data["image"] as String
//                        val description = data["description"] as String
//                        news.add(addnewsModel(id, title,author,date,description,image))
//                    }
//                    recycleView1.layoutManager = LinearLayoutManager(activity!!)
//                    recycleView1.setHasFixedSize(true)
//                    val NewsAdapter = addnewsAdapter(activity!!, news)
//                    recycleView1.adapter = NewsAdapter
//
//                }
//            }
//    }



}
