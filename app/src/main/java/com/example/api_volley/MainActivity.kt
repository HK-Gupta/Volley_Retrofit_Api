package com.example.api_volley

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // 35.46
    private val url = "https://api.github.com/users"
    private var userInfo = UserInfo()
    private var userInfoItem = arrayOf<UserInfoItem>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.details_recycler_view)
        val recyclerViewRetrofit: RecyclerView = findViewById(R.id.details_recycler_view_retrofit)


        // Creating a string request variable that takes the string from the api.
        val stringRequest = StringRequest(url, {


            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()
            userInfoItem = gson.fromJson(it, Array<UserInfoItem>::class.java)

            userInfoItem.forEach {
                userInfo.add(it)
            }

            val adapter = DetailsAdapter(this, userInfo)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter


        }, {
            Toast.makeText(this, "Something went wrong/n + $it", Toast.LENGTH_LONG).show()
        })


        val volley = Volley.newRequestQueue(this)
        volley.add(stringRequest)


        // Retrofit Method
        val retrofitApi = RetrofitBuilder.getInstance().create(RetrofitInterface::class.java)
        GlobalScope.launch {
            val res = retrofitApi.getDetailsRetrofit()
            val adapter = res.body()?.let { DetailsAdapter(this@MainActivity, it) }
            recyclerViewRetrofit.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewRetrofit.adapter = adapter
        }

    }
}