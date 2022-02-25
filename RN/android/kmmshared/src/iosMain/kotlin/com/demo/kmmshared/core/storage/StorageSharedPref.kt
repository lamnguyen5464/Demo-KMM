package com.demo.kmmshared.core.storage

import platform.Foundation.NSUserDefaults

actual class StorageSharedPref {
    actual fun saveString(key: String, value: String?) {
         NSUserDefaults.standardUserDefaults.setObject(value, key)
    }

    actual fun getString(key: String): String? {
        return NSUserDefaults.standardUserDefaults.stringForKey(key)
    }
}