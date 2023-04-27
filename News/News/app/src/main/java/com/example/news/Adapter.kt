package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONObject

class Adapter(val h: ClickListenerAction) : RecyclerView.Adapter<ViewHolder>() {
    //                                                                     V-return type ViewHolder
    val data = ArrayList<JSONObject>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View //the harshObjectized item_view.xml will be stored here

        view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        /**do more research*/

        val holder = ViewHolder(view)
        view.setOnClickListener {
            h.onClick(holder)
        }


        return holder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, idx: Int) {
        val title = data[idx].getString("title")
        val imgURL = data[idx].getString("urlToImage")
        val url = data[idx].getString("url")
        val author = data[idx].getString("author")
        viewHolder.title.text = title
        viewHolder.url = url
        if(!author.equals("null"))
            viewHolder.auth.text= author
        if(!imgURL.equals("null")){
            Glide.with(viewHolder.img.context).load(imgURL).into(viewHolder.img)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(fetchedData: ArrayList<JSONObject>) {
        data.clear()
        data.addAll(fetchedData)
        notifyDataSetChanged()
    }
}


interface ClickListenerAction {
    fun onClick(item: ViewHolder)
}