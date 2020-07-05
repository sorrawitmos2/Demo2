package com.example.demo2.service

import com.example.demo2.model.Movies
import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkAPI{

    @GET("movies.json")
    fun getAllMovies(): Observable<List<Movies>>

}