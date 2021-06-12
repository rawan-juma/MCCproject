package com.example.mccproject.fragments


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mccproject.R
import com.example.mccproject.adapter.UrgentAdapter
import com.example.myapplication.ApiURL
import com.example.myapplication.ArticlesModel
import com.example.myapplication.HeadlinesModel
import kotlinx.android.synthetic.main.fragment_urgent.*
import kotlinx.android.synthetic.main.fragment_urgent.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UrgentFragment : Fragment(){
    var dialog: Dialog? = null
    val API_KEY = "8ba1260833284db9bce1dcf04ab96845"
    var adapter: UrgentAdapter? = null
    var articles: List<ArticlesModel> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_urgent, container, false)

        dialog = Dialog(activity!!)

        root.recycleView_urgent.layoutManager = LinearLayoutManager(activity!!)



        root.swipeRefresh_urgent.setOnRefreshListener {
            getDataJson(API_KEY, root)

        }
        getDataJson(API_KEY, root)

        return root


    }



    fun getDataJson(apiKey: String,root: View) {

        root.swipeRefresh_urgent.isRefreshing = true
        val call: Call<HeadlinesModel?> = ApiURL.instance!!.api.getUrgentData(
                "القدس", apiKey, "ar","publishedAt",7)!!


        call.enqueue(object : Callback<HeadlinesModel?> {
            override fun onResponse(call: Call<HeadlinesModel?>?, response: Response<HeadlinesModel?>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    root.swipeRefresh_urgent.isRefreshing = false
                    articles = response.body()!!.articles!!
                    adapter = UrgentAdapter(activity!!, articles)
                    recycleView_urgent.adapter = adapter
                }
            }


            override fun onFailure(call: Call<HeadlinesModel?>?, t: Throwable) {
                root.swipeRefresh_urgent.isRefreshing = false
                Toast.makeText(activity!!, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }


}