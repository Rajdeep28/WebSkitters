package com.rajdeep.assesmentwebskitters.api

import com.rajdeep.assesmentwebskitters.PhotosModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    fun getPhotos(): Call<MutableList<PhotosModel>>
}