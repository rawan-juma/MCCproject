package com.example.mccproject.Activities

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mccproject.MainActivity
import com.example.mccproject.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_add_news.*
import java.io.ByteArrayOutputStream
import java.util.*


class AddNews : AppCompatActivity() {
    private var fileURI: Uri?=null
    lateinit var db: FirebaseFirestore
    val TAG="raw"
    var idDoc=""
    lateinit var progressDialog: ProgressDialog
    private val PICK_IMAGE_REQUEST=111
    var imageURI: Uri?=null

    var playerr: SimpleExoPlayer? =null
    private var playReady =true
    private  var currentWindow = 0
    private var playedPostion:Long = 0
    private var filePath: Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        supportActionBar!!.title="إضافة خبر"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // design Date picker "just"
        editTextDate.setOnClickListener {
            datePickerDialog()
        }

        btn_vi.setOnClickListener {
            uploadVideo()
            idDoc = "1"
            video_view_add.visibility = View.VISIBLE
            imageViewadd.visibility = View.GONE
        }


        db= Firebase.firestore
        val storage=Firebase.storage
        val storageRef=storage!!.reference
        val imageRef=storageRef.child("imagee")
        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("جاري التحميل")
        progressDialog.setCancelable(false)
        btn_img.setOnClickListener {

            idDoc ="2"
            val intent= Intent()
            intent.action=Intent.ACTION_PICK
            intent.type="image/*"
            startActivityForResult(intent,PICK_IMAGE_REQUEST)
             releaseVideo()
            video_view_add.visibility = View.GONE
            imageViewadd.visibility = View.VISIBLE
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

            if (idDoc == "2"){
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
                        addNews(title,author,date,description,uri.toString(),"2"/*,edlocation.text.toString()*/)
                        progressDialog.dismiss()
//                    supportFragmentManager.beginTransaction().replace(R.id.mainContainer,
//                        HistoricalInformation()).commit()
                        var i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    }

                }
            }else if (idDoc == "1"){
                progressDialog.show()
                val childRef=storageRef.child("Vedio/" + UUID.randomUUID().toString())
                val uploadTask = childRef?.putFile(filePath!!)

                uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    return@Continuation childRef.downloadUrl
                })?.addOnFailureListener {
                    Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
                    // Handle unsuccessful uploads
                }.addOnSuccessListener {
                    Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
                    childRef.downloadUrl.addOnSuccessListener { uri ->
                        Toast.makeText(this,"$uri", Toast.LENGTH_SHORT).show()
                        filePath=uri
                        addNews(title,author,date,description,uri.toString(),"1"/*,edlocation.text.toString()*/)
                        progressDialog.dismiss()
                        var i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    }

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
        if (resultCode == RESULT_OK){
            if (requestCode == PICK_IMAGE_REQUEST ) {
                imageURI = data!!.data
                Log.e("image",  imageURI.toString())
                imageViewadd.setImageURI(imageURI)

            }else   if (requestCode == 1) {

                filePath = data!!.data
            }
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
    private fun addNews(title: String,author:String,date:String,description:String,image:String,type:String) {
        var news =
                hashMapOf(
                        "title" to title,
                        "author" to author,
                        "date" to date,
                        "description" to description,
                        "image" to image,
                        "type" to type

                )

        db.collection("News").add(news).addOnSuccessListener {documentReference ->
            Log.e(TAG, "News added Successfully with product id ${documentReference.id} ")

        }.addOnFailureListener { exception ->
            Log.e(TAG, "News added Falid")

        }

    }

    private fun uploadVideo() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Select Video"), 1)
    }

    fun initVideo( ){
        playerr= ExoPlayerFactory.newSimpleInstance(this)

        video_view_add.player =playerr!!
        var uri = filePath
        var dataSource = DefaultDataSourceFactory(this,"exoplayer-codelab")
        var mediaSource : MediaSource = ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri)
        playerr!!.playWhenReady = playReady
        playerr!!.seekTo(currentWindow,playedPostion)
        playerr!!.prepare(mediaSource,false,false)

    }

    fun releaseVideo(){
        if (playerr != null){
            playReady = playerr!!.playWhenReady
            playedPostion = playerr!!.currentPosition
            currentWindow = playerr!!.currentWindowIndex
            playerr!!.release()
            playerr = null
        }
    }



    override fun onStart() {
        super.onStart()
        initVideo()

    }

    override fun onResume() {
        super.onResume()
        if (playerr != null) {
         initVideo()
        }
    }

    override fun onStop() {
        super.onStop()
        releaseVideo()
    }

    override fun onPause() {
        super.onPause()
        releaseVideo()
    }

}