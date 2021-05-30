package com.example.mccproject.model

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.UserManager

class RegisterDBhelper (activity: Activity) :
    SQLiteOpenHelper(activity, DATABASE_NAME, null, DATABASE_VERSION) {

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(UsersModel.TABLE_CREATE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${UsersModel.TABLE_NAME}")

        onCreate(db)
    }


    fun insertUser(username:String,email:String,password:String): Boolean {


        val cv = ContentValues()
        cv.put(UsersModel.COL_USER_NAME,username)
        cv.put(UsersModel.COL_EMAIL,email)
        cv.put(UsersModel.COL_PASSWORD,password)

        return db.insert(UsersModel.TABLE_NAME, null, cv) > 0
    }




    fun getAllProductRec(): ArrayList<UsersModel> {
        val data = ArrayList<UsersModel>()
        val c =
            db.rawQuery("select * from ${UsersModel.TABLE_NAME}", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = UsersModel(c.getInt(0),c.getString(1), c.getString(2), c.getString(3))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun updateNote(oldId:Int, username: String,email: String,password: String) : Boolean{
        val cv = ContentValues()
        cv.put(UsersModel.COL_USER_NAME, username)
        cv.put(UsersModel.COL_EMAIL, email)
        cv.put(UsersModel.COL_PASSWORD, password)
        return db.update(UsersModel.TABLE_NAME,cv,"${UsersModel.COL_ID} = $oldId",null)>0
    }
    companion object {
        val DATABASE_NAME = "ShoppingCocktail"
        val DATABASE_VERSION = 1

    }

    }
