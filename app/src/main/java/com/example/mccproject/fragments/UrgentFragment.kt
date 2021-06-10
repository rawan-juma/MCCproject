package com.example.mccproject.fragments


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mccproject.Activities.AddNews
import com.example.mccproject.R
import com.example.mccproject.adapter.UrgentAdapter
import com.example.mccproject.adapter.addnewsAdapter
import com.example.myapplication.ApiClient
import com.example.myapplication.Articles
import com.example.myapplication.Headlines
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_latest_news.*
import kotlinx.android.synthetic.main.fragment_latest_news.view.*
import kotlinx.android.synthetic.main.fragment_urgent.*
import kotlinx.android.synthetic.main.fragment_urgent.view.*
import kotlinx.android.synthetic.main.select_time_new_pop_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class UrgentFragment : Fragment(){
    var dialog: Dialog? = null
    val API_KEY = "8ba1260833284db9bce1dcf04ab96845"
    var adapter: UrgentAdapter? = null
    var articles: List<Articles> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_urgent, container, false)

        dialog = Dialog(activity!!)

        root.recycleView_urgent.layoutManager = LinearLayoutManager(activity!!)



        root.swipeRefresh_urgent.setOnRefreshListener {
            retrieveJson(API_KEY, root)

        }
        retrieveJson(API_KEY, root)

        return root


    }



    fun retrieveJson(apiKey: String,root: View) {

        root.swipeRefresh_urgent.isRefreshing = true
        val call: Call<Headlines?> = ApiClient.instance!!.api.getUrgentData(
                "القدس", apiKey, "ar","publishedAt",7
        )!!


        call.enqueue(object : Callback<Headlines?> {
            override fun onResponse(call: Call<Headlines?>?, response: Response<Headlines?>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    root.swipeRefresh_urgent.isRefreshing = false
                    articles = response.body()!!.articles!!
                    adapter = UrgentAdapter(activity!!, articles)
                    recycleView_urgent.adapter = adapter
                }
            }


            override fun onFailure(call: Call<Headlines?>?, t: Throwable) {
                root.swipeRefresh_urgent.isRefreshing = false
                Toast.makeText(activity!!, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }


}