package com.example.qoutesapplication

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyler:RecyclerView
    private lateinit var adapter:myadapter
    private lateinit var listoi:ArrayList<myapidataItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var retrofit=Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(myinterface::class.java)
        recyler=findViewById(R.id.recy)
        listoi= ArrayList()
        supportActionBar?.hide()
        var progresoi:ProgressDialog= ProgressDialog(this)
        progresoi.setTitle("andorid app is loading")
        progresoi.show()
        adapter=myadapter(this,listoi)
        recyler.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        recyler.adapter=adapter
        var myapi=retrofit.getdatasoi().enqueue(object : Callback<List<myapidataItem>?> {
            override fun onResponse(
                call: Call<List<myapidataItem>?>,
                response: Response<List<myapidataItem>?>
            ) {
                for(data in response.body()!!)
                {
                    listoi.add((data))
                }
                adapter.notifyDataSetChanged()
                progresoi.dismiss()

            }

            override fun onFailure(call: Call<List<myapidataItem>?>, t: Throwable) {

            }
        })
    }
}