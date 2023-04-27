package com.example.news

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.news.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        val clickAction = object : ClickListenerAction {
            override fun onClick(item: ViewHolder) {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(item.url)
//                startActivity(intent)
                //i mean we caaan use intent
                //but we will use custom tabs
                // it will open customizable google into our app(just like gmail)

                val customTabsBuilder = CustomTabsIntent.Builder()
                val customTabsIntent = customTabsBuilder.build()
                customTabsIntent.launchUrl(item.img.context,Uri.parse(item.url))
                //try changing color of toolbar(top bar)
            }
        }
        binding.newsRecyclerView.adapter = Adapter(clickAction)
        getList(binding.newsRecyclerView.adapter as Adapter)
    }

    private fun getList(adapter: Adapter): JSONObject? {
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://newsapi.org/v2/top-headlines?country=in&apiKey=30e4c87b041a462ca02a526bf751fa2c"
        val ans = JSONObject()
        val request = object : JsonObjectRequest(Request.Method.GET, url, null, { response ->
            run {
                val newsArray = response.getJSONArray("articles")
                val newsArrayList = ArrayList<JSONObject>()
                for (i in 0 until newsArray.length()) {
                    newsArrayList.add(newsArray.getJSONObject(i))
                }
                adapter.updateData(newsArrayList)
            }
        }, {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
        }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
        }
        queue.add(request)
        return ans
    }
}