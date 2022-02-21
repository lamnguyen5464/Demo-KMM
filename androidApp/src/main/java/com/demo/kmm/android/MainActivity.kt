package com.demo.kmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.demo.kmm.Greeting
import android.widget.TextView
import com.demo.kmm.ApiRequest
import com.demo.kmm.AppSocket
import kotlinx.coroutines.*

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

        runBlocking {
            launch {
                try {
                    apiRequest.getCatFact().let {
                        Log.d("@@@", it.fact)
                        tv.text = it.fact
                    }
                } catch (e: Exception) {
                    Log.d("@@@", e.message!!)
                }
            }
        }

        initSocket()
    }

    fun initSocket() {
        val URL = "https://drawing-server-5464.herokuapp.com/"
        AppSocket(URL).run {
            connect()
            stateListener = {
//                send("Tmp")
                Log.d("@@@", it.name)
            }
        }
    }
}
