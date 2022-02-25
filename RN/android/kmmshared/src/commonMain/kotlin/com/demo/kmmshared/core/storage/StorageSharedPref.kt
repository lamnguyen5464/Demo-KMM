package com.demo.kmmshared.core.storage

expect class StorageSharedPref

expect fun StorageSharedPref.saveString(key: String, value: String?)
expect fun StorageSharedPref.getString(key: String): String?