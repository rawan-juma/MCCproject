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
import com.example.mccproject.R
import com.example.mccproject.fragments.UrgentFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_add_news.*
import java.io.ByteArrayOutputStream
import java.util.*

class AddNews : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    val TAG = "News"
    private val PICK_IMAGE_REQUEST = 100
    var imageURI: Uri? = null
    private var progressDialog: ProgressDialog? = null
    private var fileURI: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        supportActionBar!!.title="إضافة خبر"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // design Date picker "just"
        editTextDate.setOnClickListener {
            datePickerDialog()
        }

        db = Firebase.firestore
        val storage = Firebase.storage.getReference()
        val storageRef = storage
        val imageRef = storageRef.child("new")
        imageView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
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


            showDialog()
            // Get the data from an ImageView as bytes
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val data = baos.toByteArray()
            val childRef = imageRef.child(System.currentTimeMillis().toString() + "Newsimage.png")
            var uploadTask = childRef.putBytes(data)
            uploadTask.addOnFailureListener { exception ->
                Log.e(TAG, "Image uploaded falid")
                hideDialog()
            }.addOnSuccessListener {
                Log.e(TAG, "Image uploaded succeful")
                childRef.downloadUrl.addOnSuccessListener { uri ->
                    Log.e(TAG, uri.toString())
                    fileURI = uri
                    addNews(title,author,date,description,uri.toString())
                }

                supportFragmentManager.beginTransaction().replace(android.R.id.content, UrgentFragment()).commit()
                hideDialog()
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
            imageView.setImageURI(imageURI)

        }
    }

    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Uploading image ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hideDialog() {
        if (progressDialog!!.isShowing)
            progressDialog!!.dismiss()
    }
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