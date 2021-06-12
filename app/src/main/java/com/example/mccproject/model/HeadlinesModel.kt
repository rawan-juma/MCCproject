package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class HeadlinesModel {

    @SerializedName("articles")
    @Expose
    var articles: List<ArticlesModel>? = null
}