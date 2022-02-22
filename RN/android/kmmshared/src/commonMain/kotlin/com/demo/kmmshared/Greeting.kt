package com.demo.kmmshared

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}