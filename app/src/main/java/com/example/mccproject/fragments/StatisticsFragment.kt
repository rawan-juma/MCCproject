package com.example.mccproject.fragments


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mccproject.Activities.FormStatistics
import com.example.mccproject.R
import kotlinx.android.synthetic.main.fragment_statistics.view.*
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel


/**
 * A simple [Fragment] subclass.
 */
class StatisticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)
        root.btn_pie_chart_edit.setOnClickListener {
            var i = Intent(context, FormStatistics::class.java)
            startActivity(i)
        }

        root.btn_bar_chart_edit.setOnClickListener {
            var i = Intent(context,FormStatistics::class.java)
            startActivity(i)
        }

        //call function pie chart
        pieChart(root)
        //call function bar chart
        barChart(root)
        return root
    }
    fun pieChart(root:View){
        //select the properties pie chart (name,number,color)
        root.piechart.addPieSlice(PieModel("الشهداء", 15f, Color.parseColor("#FE6DA8")))
        root.piechart.addPieSlice(PieModel("الاعتقالات", 25f, Color.parseColor("#56B7F1")))
        root.piechart.addPieSlice(PieModel("حجز المنازل", 35f, Color.parseColor("#CDA67F")))
        root.piechart.addPieSlice(PieModel("هدم المنازل", 9f, Color.parseColor("#FED70E")))
        root.piechart.startAnimation()
    }
    fun barChart(root :View){
        //select the properties bar chart (name,number,color)
        root.barchart.addBar(BarModel(2.3f, -0xedcbaa))
        root.barchart.addBar(BarModel(2f, -0xcbcbaa))
        root.barchart.addBar(BarModel(3.3f, -0xa9cbaa))
        root.barchart.addBar(BarModel(1.1f, -0x78c0aa))
        root.barchart.addBar(BarModel(2.7f, -0xa9480f))
        root.barchart.addBar(BarModel(2f, -0xcbcbaa))
        root.barchart.addBar(BarModel(0.4f, -0xe00b54))
        root.barchart.addBar(BarModel(4f, -0xe45b1a))

        root.barchart.startAnimation()
    }

}
