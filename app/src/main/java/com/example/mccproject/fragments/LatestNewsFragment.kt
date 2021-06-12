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
import com.example.mccproject.R
import com.example.mccproject.adapter.addnewsAdapter
import com.example.myapplication.ApiURL
import com.example.myapplication.ArticlesModel
import com.example.myapplication.HeadlinesModel
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
    var addAdapter: addnewsAdapter? = null
    var articles: List<ArticlesModel> = ArrayList()
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
                getDataJson(

                    API_KEY, dialog!!.et_from.text.toString(), dialog!!.et_to.text.toString()
                    ,root)

            }

            btnClose.setOnClickListener { dialog!!.dismiss() }
        }

        root.swipe_refresh_last_news.setOnRefreshListener {
            getDataJson(API_KEY, fromDatee, toDatee,root)
        }
        getDataJson(API_KEY, fromDatee, toDatee,root)

        return root




    }

    fun getDataJson(apiKey: String, fromDate: String?, toDate: String?,root: View) {

        root.swipe_refresh_last_news.isRefreshing = true
        val call: Call<HeadlinesModel?> = ApiURL.instance!!.api.getSpecificData(
            "القدس", fromDate, toDate, apiKey, "ar","relevancy",100
        ,)!!


        call.enqueue(object : Callback<HeadlinesModel?> {
            override fun onResponse(call: Call<HeadlinesModel?>?, response: Response<HeadlinesModel?>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    root.swipe_refresh_last_news.isRefreshing = false
                    articles = response.body()!!.articles!!
                    addAdapter = addnewsAdapter(activity!!, articles)
                    rc_last_new.adapter = addAdapter
                }
            }

            override fun onFailure(call: Call<HeadlinesModel?>?, t: Throwable) {
                root.swipe_refresh_last_news.isRefreshing = false
                Toast.makeText(activity!!, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }


}

