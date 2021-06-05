package com.example.mccproject.fragments


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mccproject.Activities.AddNews
import com.example.mccproject.R
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    var imageURI:String=" "
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
        var pass=sharedPreferences.getString("passSignUp","")
//        edPassword.setText("$pass")


//        root.profile_image.setImageURI(Uri.parse(imageURI))
//
//        root.profile_image.setOnClickListener{
//            PickImageDialog.build(PickSetup())
//                .setOnPickResult { r ->
//                    imageURI = r.uri.toString()
//                    root.profile_image.setImageURI(r.uri)
//                }
//                .setOnPickCancel {Toast.makeText(activity,"Faild to get the image",Toast.LENGTH_SHORT).show()
//
//                }.show(activity!!.supportFragmentManager)
//        }

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


        root.button.setOnClickListener {
            val view = LayoutInflater.from(context).inflate(R.layout.activity_edit_profile, null)
//            root.edUsername.setText(name)
//            email = root.edEmail.text.toString()
//            pass =root.edPassword.text.toString()
            val builder = AlertDialog.Builder(activity)
                .setView(view)
                .setPositiveButton("تعديل") { _, _ ->
//                    val editor = sharedPreferences.edit()
//                    editor.putString("username",root.)
//                    editor.putString("email",email)
//                    editor.putString("passSignUp",pass)
////                    editor.putString("image",imageURI)
//                    editor.apply()
//                    activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, ProfileFragment()).commit()
////                val db = NoteDBHelper(activity)
////                val isUpdate = db.updateNote(
////                    data.get(position).id,
////                    view.edittitle.text.toString(),
////                    view.editbody.text.toString()
////                )
////                if (isUpdate == true) {
////                    data[position].title = view.edittitle.text.toString()
////                    data[position].body = view.editbody.text.toString()
////                    notifyDataSetChanged()
////
////                    Toast.makeText(activity, "Updated Successfully", Toast.LENGTH_SHORT)
////                        .show()
////                } else {
////                    Toast.makeText(activity, "Error Updated", Toast.LENGTH_SHORT).show()
////
////                }
                }.setNegativeButton("إلغاء") { _, _ ->


                }
            val alertDialog = builder.create()
            alertDialog.show()
        }


        return root
    }


}
