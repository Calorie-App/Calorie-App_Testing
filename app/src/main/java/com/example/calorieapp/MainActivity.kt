package com.example.calorieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


import okhttp3.Call
import okhttp3.Callback

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.apiTester)
        val button = findViewById<Button>(R.id.search_button)
        button.setOnClickListener(){
            val userInput = findViewById<EditText>(R.id.food_answer).text.toString()
            getNutritionInfo(textView, userInput)
        }

    }


    private fun getNutritionInfo(textView: TextView, food: String) {
        val client = OkHttpClient()
        val url = "https://api.api-ninjas.com/v1/nutrition?query=${food}"
        val request = Request.Builder().url(url)
            .addHeader("x-api-key", "QpWHeJe+v61Szjf1tIYvPg==edPDwj9M04faGItV").build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        textView.text = response.body!!.string()
                    }
                }
            }
        })
    }
}