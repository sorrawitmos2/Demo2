package com.example.demo2.model

import com.google.gson.annotations.SerializedName

class Movies (@SerializedName("id") val id: Int,
              @SerializedName("thumbnail") val thumbnail: String,
              @SerializedName("title") val title: String,
              @SerializedName("excerpt") val excerpt: String,
              @SerializedName("categories") val categories :ArrayList<String> ){





}