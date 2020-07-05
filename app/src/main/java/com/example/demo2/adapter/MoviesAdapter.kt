package com.example.demo2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demo2.model.Movies
import com.example.demo2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout.view.*

class MoviesAdapter(val moviesList: ArrayList<Movies>, val context: Context?) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.grid_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.title_textView.text = moviesList[position].title

        var list = moviesList[position].categories
        val stringOfNames = list.joinToString(", ")


        holder.itemView.des_textView.text = "categories : " + stringOfNames

        Picasso.get().load(moviesList[position].thumbnail)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.itemView.icon_imageView);


        holder.itemView.setOnClickListener(View.OnClickListener { v ->

            Toast.makeText(v.getContext(), moviesList[position].title, Toast.LENGTH_SHORT).show();
        })
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)


}

