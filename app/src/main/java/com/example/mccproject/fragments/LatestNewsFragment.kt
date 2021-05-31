package com.example.mccproject.fragments


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mccproject.Activities.Detailes
import com.example.mccproject.R
import com.example.mccproject.adapter.addnewsAdapter
import com.example.myapplication.ApiClient
import com.example.myapplication.Articles
import com.example.myapplication.Headlines
import kotlinx.android.synthetic.main.fragment_latest_news.*
import kotlinx.android.synthetic.main.fragment_latest_news.view.*
import kotlinx.android.synthetic.main.select_time_new_pop_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class LatestNewsFragment : Fragment(){
    var dialog: Dialog? = null
    val API_KEY = "8ba1260833284db9bce1dcf04ab96845"
    var adapter: addnewsAdapter? = null
    var articles: List<Articles> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_latest_news, container, false)
       // supportActionBar!!.title = "آخر الأخبار"
        dialog = Dialog(activity!!)

        root.rc_last_new.layoutManager = LinearLayoutManager(activity!!)
        var fromDatee: String? = null
        var toDatee: String? = null
        root.btn_date_new.setOnClickListener {

            dialog!!.setContentView(R.layout.select_time_new_pop_up)
            dialog!!.show()
            val btnClose: Button = dialog!!.findViewById(R.id.close)
            dialog!!.btn_save_date.setOnClickListener {
                fromDatee = dialog!!.et_from.text.toString()
                toDatee = dialog!!.et_to.text.toString()
                dialog!!.dismiss()
                retrieveJson(

                    API_KEY, dialog!!.et_from.text.toString(), dialog!!.et_to.text.toString()
                    ,root)

            }

            btnClose.setOnClickListener { dialog!!.dismiss() }
        }

        root.swipe_refresh_last_news.setOnRefreshListener {
            retrieveJson(API_KEY, fromDatee, toDatee,root)
        }
        retrieveJson(API_KEY, fromDatee, toDatee,root)

        return root




    }

    fun retrieveJson(apiKey: String, fromDate: String?, toDate: String?,root: View) {

        root.swipe_refresh_last_news.isRefreshing = true
        val call: Call<Headlines?> = ApiClient.instance!!.api.getSpecificData(
            "القدس", fromDate, toDate, apiKey, "ar"
        )!!


        call.enqueue(object : Callback<Headlines?> {
            override fun onResponse(call: Call<Headlines?>?, response: Response<Headlines?>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    root.swipe_refresh_last_news.isRefreshing = false
                    articles = response.body()!!.articles!!
                    adapter = addnewsAdapter(activity!!, articles)
                    rc_last_new.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Headlines?>?, t: Throwable) {
                root.swipe_refresh_last_news.isRefreshing = false
                Toast.makeText(activity!!, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }


}

