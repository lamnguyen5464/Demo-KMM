package com.demo.kmmshared.core.crypto

expect class EncryptAES() {
    fun encryptDecrypt(
        _inputText: String?,
        _encryptionKey: String,
        _initVector: String
    ): String?
}