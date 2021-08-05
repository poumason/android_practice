package com.example.myfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.myfeed.adapter.ItemAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            val apiService = ApiClientManager.client.create(ApiService::class.java)
//            apiService.index().enqueue(object : Callback<List<Posts>> {
//                override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
//                    val sb = StringBuffer()
//                    val list = response.body()
//
//                    for (p in list!!) {
//                        sb.append(p.body)
//                        sb.append("\n")
//                        sb.append("---------------------\n")
//                        break
//                    }
//
//                    println(sb.toString())
//                }
//
//                override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//
//                }
//
//            })
//        }
        getData()
    }

    private fun getData() {
        val apiService = ApiClientManager.client.create(ApiService::class.java)
        apiService.index().enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                val data = response.body()

                if (data == null || data.isEmpty()) {
                    println("no data")
                    return
                }

                val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                recyclerView.adapter = ItemAdapter(data)
                recyclerView.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                throw t
            }

        })
    }
}
