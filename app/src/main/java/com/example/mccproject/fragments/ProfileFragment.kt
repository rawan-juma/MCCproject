package com.example.mccproject.fragments


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mccproject.Activities.AddNews
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_add_news.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import kotlinx.android.synthetic.main.activity_histori_news_details.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import petrov.kristiyan.colorpicker.ColorPicker


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    var imageURI:String=" "
    private  var FontSize = 14f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_profile, container, false)


        val sharedPreferences= requireActivity().getSharedPreferences("shared", Context.MODE_PRIVATE)
        var name= sharedPreferences.getString("username","")
        root.username.text = name
        var email=sharedPreferences.getString("email","")
        root.useremail.text = email

        if(email.equals("admin@gmail.com")){
            var play = root.button3
            play.isClickable=false
            play.visibility=View.VISIBLE
        }else{
            var play = root.button3
            play.isClickable=false
            play.visibility=View.INVISIBLE
        }


        root.colorPicker.setOnClickListener {
            val colorPicker = ColorPicker(activity)
            colorPicker.show()
            colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    txt1.setTextColor(color)
                    txt2.setTextColor(color)
                    //  tvTitleHistori.setBackgroundColor(Color.parseColor("#FF018786"))
                }

                override fun onCancel() {
                    // put code
                }
            })
            txt1.text
            txt2.text

        }
        root.increase.setOnClickListener {
            FontSize +=4f
            txt1.setTextSize(TypedValue.COMPLEX_UNIT_SP,FontSize)
            txt2.setTextSize(TypedValue.COMPLEX_UNIT_SP,FontSize)
        }
        root.decrease.setOnClickListener {
            FontSize -=4f
            txt1.setTextSize(TypedValue.COMPLEX_UNIT_SP,FontSize)
            txt2.setTextSize(TypedValue.COMPLEX_UNIT_SP,FontSize)
        }
//        root.fontSize.setOnClickListener {
//
//            val builder = AlertDialog.Builder(context)
//            builder.setTitle("حجم الخط")
//            val sizes = arrayOf("كبير", "متوسط", "صغير")
//            val checkedItem = 1
//
//            builder.setSingleChoiceItems(
//                sizes, checkedItem
//            ) { dialog, which ->
//            }
//            builder.setPositiveButton("Done") { _, _ ->
//            }
//            builder.setNegativeButton("Cancel", null)
//            val dialog = builder.create()
//            dialog.show()
//
//        }

        root.button3.setOnClickListener {
            var i = Intent(context,AddNews::class.java)
            startActivity(i)
        }





        return root
    }


}
