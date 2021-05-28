package com.example.mccproject.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cloudfinal.adapter.newsAdapter
import com.example.mccproject.Activities.Detailes
import com.example.mccproject.Activities.SignIn
import com.example.mccproject.R


/**
 * A simple [Fragment] subclass.
 */
class LatestNewsFragment : Fragment(),newsAdapter.onClick{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_latest_news, container, false)
       // supportActionBar!!.title = "آخر الأخبار"
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  supportActionBar!!.title = ""
    }

    override fun onClickItem(position: Int) {
        var i = Intent(activity, Detailes::class.java)
        startActivity(i)
    }
}
