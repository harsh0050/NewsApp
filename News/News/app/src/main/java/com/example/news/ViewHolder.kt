package com.example.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//this item view is item_view.xml harshObject
//so, we need to pass harshObject into ViewHolder.
//to do so, onCreatViewHolder method is used
//in that method, ViewHolder is created and item_view.xml is passed into the parameter
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView
    val img: ImageView
    var url: String = ""
    var auth: TextView

    init {
        title = itemView.findViewById(R.id.titleView)
        img = itemView.findViewById(R.id.newsImage)
        auth = itemView.findViewById(R.id.author)
    }
}