package com.example.myapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Headlines {

    @SerializedName("articles")
    @Expose
    var articles: List<Articles>? = null
}