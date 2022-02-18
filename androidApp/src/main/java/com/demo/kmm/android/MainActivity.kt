package com.demo.kmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.demo.kmm.Greeting
import android.widget.TextView
import com.demo.kmm.ApiRequest
import com.demo.kmm.model.CatFact
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()


        val apiRequest = ApiRequest()

        val mainScope = MainScope()

        runBlocking {

            try {
//                val res: CatFact = apiRequest.getCatFact()
//                Log.d("@@@", res.fact)

                apiRequest.getCatFact().also {
                    Log.d("@@@", it.fact)
                }
            } catch (e: Exception) {
                Log.d("@@@", e.message!!)
            }
        }


    }
}
