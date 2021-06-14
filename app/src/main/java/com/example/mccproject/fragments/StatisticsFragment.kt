package com.example.mccproject.fragments


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mccproject.Activities.FormStatistics
import com.example.mccproject.R
import com.example.mccproject.model.statisticsModel
import com.example.mccproject.model.statisticsModel2
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.fragment_statistics.view.*
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel


/**
 * A simple [Fragment] subclass.
 */
class StatisticsFragment : Fragment() {
    val fb = FirebaseFirestore.getInstance()
    var staticN = 0f
    var staticS = 0f
    var staticA = 0f
    var staticD = 0f
    var staticCN = 0f
    var staticCS = 0f
    var staticCA = 0f
    var staticCD = 0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)

        val sharedPreferences= requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE)
     //   var name= sharedPreferences.getString("username","")

        var email=sharedPreferences.getString("email","")
        if(email.equals("admin@gmail.com")){
            var play = root.btn_bar_chart_edit
            play.isClickable=false
            play.visibility=View.VISIBLE
            var play2 = root.btn_pie_chart_edit
            play2.isClickable=false
            play2.visibility=View.VISIBLE
        }else{
            var play = root.btn_bar_chart_edit
            play.isClickable=false
            play.visibility=View.INVISIBLE
            var play2 = root.btn_pie_chart_edit
            play2.isClickable=false
            play2.visibility=View.INVISIBLE
        }
        root.btn_bar_chart_edit.setOnClickListener {
            var i = Intent(context, FormStatistics::class.java)
            startActivity(i)

        }
        getStatistics2()
        root.btn_pie_chart_edit.setOnClickListener {
            var i = Intent(context, FormStatistics::class.java)
            startActivity(i)
        }
        getStatistics()
        //call function pie chart
       // pieChart(root)
        //call function bar chart
        barChart(root)
        return root
    }

    fun pieChart(root: View) {
        //select the properties pie chart (name,number,color)
//        root.piechart.addPieSlice(PieModel("الشهداء", S, Color.parseColor("#FE6DA8")))
//        root.piechart.addPieSlice(PieModel("الاعتقالات", A, Color.parseColor("#56B7F1")))
//        root.piechart.addPieSlice(PieModel("حجز المنازل", H, Color.parseColor("#CDA67F")))
//        root.piechart.addPieSlice(PieModel("هدم المنازل", D, Color.parseColor("#FED70E")))
        root.piechart.startAnimation()
    }

    fun barChart(root: View) {
        //select the properties bar chart (name,number,color)
//        root.barchart.addBar(BarModel(2.3f, -0xedcbaa))
//        root.barchart.addBar(BarModel(2f, -0xcbcbaa))
//        root.barchart.addBar(BarModel(3.3f, -0xa9cbaa))
//        root.barchart.addBar(BarModel(1.1f, -0x78c0aa))


        root.barchart.startAnimation()
    }

    private fun getStatistics() {
        val statistics = mutableListOf<statisticsModel>()
        fb.collection("statistics")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val statictNum = data["statictNum"] as String
                        val typestatic = data["typestatic"] as String
                        Log.e("ststst", "///////////////+$statictNum+$typestatic")

                        if (typestatic == "حجز المنازل") {
                            //count++
                            staticN += statictNum.toFloat()
                            //  H = staticN /4f*100

                            Log.e("hhhhh", "///////////////+$staticN")

                        } else if (typestatic == "الشهداء") {
                            //count++
                            staticS += statictNum.toFloat()
                            //  S = staticS /4f*100

                            Log.e("sssssss", "///////////////+$staticS")

                        } else if (typestatic == "الاعتقالات") {
                            // count++
                            staticA += statictNum.toFloat()
                            //  A = staticA /4f*100

                            Log.e("aaaaaaaaa", "///////////////+$staticA")

                        } else if (typestatic == "هدم المنازل") {
                            //count++
                            staticD += statictNum.toFloat()
                            //  D = staticD/4f*100

                            Log.e("DDDDDD", "///////////////+$staticD")


                        }

                        statistics.add(statisticsModel(id, typestatic, statictNum))
                    }
                    piechart.addPieSlice(PieModel("حجز المنازل", staticN, Color.parseColor("#CDA67F")))
                    piechart.addPieSlice(PieModel("الشهداء", staticS, Color.parseColor("#FE6DA8")))
                    piechart.addPieSlice(PieModel("الاعتقالات", staticA, Color.parseColor("#56B7F1")))
                    piechart.addPieSlice(PieModel("هدم المنازل", staticD, Color.parseColor("#FED70E")))
                    piechart.startAnimation()
                }
            }

    }


    private fun getStatistics2() {
        val statistics = mutableListOf<statisticsModel2>()
        fb.collection("statistics2")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val statictNumC = data["statictNumC"] as String
                        val statictcolor = data["statictcolor"]  as String
                        Log.e("barststst", "///////////////+$statictNumC+$statictcolor")

                        if (statictcolor == "#CDA67F") {
                        staticCN += statictNumC.toFloat()
                        Log.e("barhhhhh", "///////////////+$staticCN")

                        } else if (statictcolor =="#FE6DA8"){
                        staticCS += statictNumC.toFloat()
                        Log.e("barsssssss", "///////////////+$staticCS")

                        }else if (statictcolor =="#56B7F1"){
                        staticCA += statictNumC.toFloat()
                        Log.e("baraaaaaaaaa", "///////////////+$staticCA")

                        }else if(statictcolor =="#FED70E") {
                              staticCD += statictNumC.toFloat()
                              Log.e("barDDDDDD", "///////////////+$staticCD")

                        }
                        statistics.add(statisticsModel2(id,statictNumC,statictcolor))


                    }
                    barchart.addBar(BarModel(staticCN, Color.parseColor("#CDA67F")))
                    barchart.addBar(BarModel(staticCS, Color.parseColor("#FE6DA8")))
                    barchart.addBar(BarModel(staticCA, Color.parseColor("#56B7F1")))
                    barchart.addBar(BarModel(staticCD, Color.parseColor("#FED70E")))
                    barchart.startAnimation()

                }
            }
    }

}
