package com.example.mccproject.fragments


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mccproject.Activities.AddNews
import com.example.mccproject.R
import kotlinx.android.synthetic.main.fragment_profile.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_profile, container, false)

        root.fontSize.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("حجم الخط")
            val sizes = arrayOf("كبير", "متوسط", "صغير")
            val checkedItem = 1

            builder.setSingleChoiceItems(
                sizes, checkedItem
            ) { dialog, which ->
            }
            builder.setPositiveButton("Done") { _, _ ->
            }
            builder.setNegativeButton("Cancel", null)
            val dialog = builder.create()
            dialog.show()

        }

        root.button3.setOnClickListener {
            var i = Intent(context,AddNews::class.java)
            startActivity(i)
        }

        return root
    }


}
