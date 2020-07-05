package com.example.demo2.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.R
import com.example.demo2.adapter.MoviesAdapter
import com.example.demo2.model.Movies
import com.example.demo2.service.INetworkAPI
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initView()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
        gridLayoutManager =
                GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)


        val url = "https://antchatbot.firebaseio.com/"
        val retrofit = Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create(
                        GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url).build()

        val postsApi = retrofit.create(INetworkAPI::class.java)
        val response = postsApi.getAllMovies()


        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
            recyclerView!!.adapter = MoviesAdapter(it as ArrayList<Movies>,this)
        }

    }
}