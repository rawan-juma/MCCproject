package com.example.mccproject.model

class UsersModel(var id:Int,var username:String?,var email:String,var password:String){

    companion object{

        val COL_ID = "id"
        val COL_USER_NAME = "username"
        val COL_EMAIL="email"
        val COL_PASSWORD="password"

        val TABLE_NAME = "User_Register"
        val TABLE_CREATE = "create table $TABLE_NAME" +
                "($COL_ID integer primary key autoincrement," +
                "$COL_USER_NAME text,$COL_EMAIL text ,$COL_PASSWORD text )"


    }

}