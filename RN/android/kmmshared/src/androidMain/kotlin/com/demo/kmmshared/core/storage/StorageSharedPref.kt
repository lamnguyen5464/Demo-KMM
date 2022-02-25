package com.demo.kmmshared.core.storage

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

const val KEY_STORAGE: String = "KEY_VALUE_STORAGE"
actual typealias StorageSharedPref = Activity

actual fun StorageSharedPref.getString(key: String): String? {
    val sharedPrefStorage: SharedPreferences =
        this.getSharedPreferences(KEY_STORAGE, MODE_PRIVATE)
    return sharedPrefStorage.getString(key, null)

}


actual fun StorageSharedPref.saveString(key: String, value: String?) {
    val sharedPrefStorage: SharedPreferences =
        this.getSharedPreferences(KEY_STORAGE, MODE_PRIVATE)
    val editor = sharedPrefStorage.edit()
    editor.putString(key, value)
    editor.apply()
}
