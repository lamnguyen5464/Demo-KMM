package com.demo.kmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.demo.kmm.Greeting
import android.widget.TextView
import com.demo.kmm.CatFactApi
import com.demo.kmm.FeatureRepository
import com.demo.kmm.model.CatFact
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
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


        val apiRequest = CatFactApi()

        val mainScope = MainScope()

        runBlocking {

            launch {
                try {

                    Log.d("@@@ ", "start request catfact")
                    val cachedCatFact = apiRequest.getFromCache()
                    Log.d("@@@ old", cachedCatFact.fact)

                    val catFact: CatFact = apiRequest.getCatFact()
                    Log.d("@@@", catFact.fact)
                    apiRequest.saveToCache(catFact)
                } catch (e: Exception) {
                    Log.d("@@@", e.message!!)
                }
            }


            launch {
                try {
                    Log.d("@@@ ", "start request features")
                    val featureRepo = FeatureRepository()
                    val res = featureRepo.getAllFeatures()
                    Log.d("@@@ features: ", res.items.toString())

                } catch (e: Exception) {
                    Log.d("@@@", e.message!!)
                }
            }
        }

    }
}
