package com.demornkmm

import android.os.Bundle
import android.util.Log
import com.demo.kmmshared.biz.feature.FeatureRepository
import com.facebook.react.ReactActivity
import com.demo.kmmshared.core.storage.StorageManager
import kotlinx.coroutines.*

class MainActivity : ReactActivity() {
    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    override fun getMainComponentName(): String? {
        return "DemoRnKmm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {

            launch {
                try {
                    Log.d("@@@ ", "start request features")
                    val featureRepo = FeatureRepository()
                    val res = featureRepo.getAllFeatures()
                    Log.d("@@@ features: ", res?.items.toString())

                } catch (e: Exception) {
                    Log.d("@@@ error", e.message!!)
                }
            }

            launch {
                try {

                    val store = StorageManager()
                    println("@@ get from storage: " + store.getString("tmp"))
                    store.saveString("temp", "value to save")
                } catch (e: Exception) {
                    Log.d("@@@ error storage", e.message!!)
                }
            }
        }
        Log.d("@@@", "Async log here")

    }
}