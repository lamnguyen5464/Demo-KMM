package com.demo.kmmshared.core.storage

class StorageManager(private val storageSharedPref: StorageSharedPref) {
    fun saveString(key: String, value: String?) {
        storageSharedPref.saveString(key, value)
    }

    fun getString(key: String): String? {
        return storageSharedPref.getString(key)
    }
}