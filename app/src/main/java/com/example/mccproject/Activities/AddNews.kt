package com.example.mccproject.Activities

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import com.example.mccproject.fragments.HistoricalInformation
import com.example.mccproject.fragments.UrgentFragment
import com.example.mccproject.model.addnewsModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_add_news.*
import java.io.ByteArrayOutputStream
import java.util.*

class AddNews : AppCompatActivity() {
    private var fileURI: Uri?=null
    lateinit var db: FirebaseFirestore
    val TAG="raw"
    var idDoc=""
    lateinit var progressDialog:ProgressDialog
    private val PICK_IMAGE_REQUEST=111
    var imageURI: Uri?=null
    var path:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        supportActionBar!!.title="إضافة خبر"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // design Date picker "just"
        editTextDate.setOnClickListener {
            datePickerDialog()
        }

        db= Firebase.firestore
        val storage=Firebase.storage
        val storageRef=storage!!.reference
        val imageRef=storageRef.child("images")
        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("جاري التحميل")
        progressDialog.setCancelable(false)
        imageViewadd.setOnClickListener {
            val intent=Intent()
            intent.action=Intent.ACTION_PICK
            intent.type="image/*"
            startActivityForResult(intent,PICK_IMAGE_REQUEST)
        }


        addnews.setOnClickListener {
            val title =editTextTitle.text.toString().trim()
            val author = editTextAuthor.text.toString().trim()
            val date = editTextDate.text.toString()
            val description = editTextTextDescription.text.toString()

            editTextTextDescription

            if (title.isEmpty()) {
                editTextTitle.error = "Title Required"
                editTextTitle.requestFocus()
                return@setOnClickListener
            }
            if (author.isEmpty()) {
                editTextAuthor.error = "Author Required"
                editTextAuthor.requestFocus()
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                editTextDate.error = "Date Required"
                editTextDate.requestFocus()
                return@setOnClickListener
            }
            if (description.isEmpty()) {
                editTextTextDescription.error = "Description Required"
                editTextTextDescription.requestFocus()
                return@setOnClickListener
            }


            progressDialog.show()
            val bitmap = (imageViewadd.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val childRef=imageRef.child(System.currentTimeMillis().toString()+"_hzmimages.png")
            var uploadTask = childRef.putBytes(data)
            uploadTask.addOnFailureListener {
                Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
                // Handle unsuccessful uploads
            }.addOnSuccessListener {
                Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
                childRef.downloadUrl.addOnSuccessListener { uri ->
                    Toast.makeText(this,"$uri", Toast.LENGTH_SHORT).show()
                    fileURI=uri
                    addNews(title,author,date,description,uri.toString()/*,edlocation.text.toString()*/)
                    progressDialog.dismiss()
//                    supportFragmentManager.beginTransaction().replace(R.id.mainContainer,
//                        HistoricalInformation()).commit()
                    var i = Intent(this,MainActivity::class.java)
                    startActivity(i)
                }

            }
        }
    }

    fun datePickerDialog(){
        val cldr = Calendar.getInstance()
        val day = cldr.get(Calendar.DAY_OF_MONTH)
        val month = cldr.get(Calendar.MONTH)
        val year = cldr.get(Calendar.YEAR)
        val picker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                editTextDate.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
            }, year, month, day
        )
        picker.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            imageURI = data!!.data
            Log.e("image",  imageURI.toString())
            imageViewadd.setImageURI(imageURI)

        }
    }

//    private fun showDialog() {
//        progressDialog = ProgressDialog(this)
//        progressDialog!!.setMessage("Uploading image ...")
//        progressDialog!!.setCancelable(false)
//        progressDialog!!.show()
//    }
//
//    private fun hideDialog() {
//        if (progressDialog!!.isShowing)
//            progressDialog!!.dismiss()
//    }
    private fun addNews(title: String,author:String,date:String,description:String,image:String) {
        var news =
            hashMapOf(
                "title" to title,
                "author" to author,
                "date" to date,
                "description" to description,
                "image" to image

            )

        db.collection("News").add(news).addOnSuccessListener {documentReference ->
            Log.e(TAG, "News added Successfully with product id ${documentReference.id} ")

        }.addOnFailureListener { exception ->
            Log.e(TAG, "News added Falid")

        }

    }
}