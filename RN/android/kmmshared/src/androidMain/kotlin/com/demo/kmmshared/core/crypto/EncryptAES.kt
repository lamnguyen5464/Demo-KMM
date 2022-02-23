package com.demo.kmmshared.core.crypto

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

actual class EncryptAES {

    enum class EncryptMode {
        ENCRYPT, DECRYPT
    }

    // cipher to be used for encryption and decryption
    private var _cxD: Cipher? = null
    private var _cxE: Cipher? = null

    // encryption key and initialization vector
    private lateinit var _key: ByteArray // encryption key and initialization vector
    private lateinit var _iv: ByteArray

    @Throws(
        UnsupportedEncodingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    actual fun encryptDecrypt(
        _inputText: String?,
        _encryptionKey: String,
        _initVector: String
    ): String? {
        val _mode = EncryptAES.EncryptMode.ENCRYPT
        if (_inputText == null || _inputText == "") {
            return ""
        }
        try {
            _cxE = Cipher.getInstance("AES/CBC/PKCS5Padding")
            _cxD = Cipher.getInstance("AES/CBC/PKCS5Padding")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        }
        _key = ByteArray(32) //256 bit key space
        _iv = ByteArray(16) //128 bit IV
        var _out: String? = "" // output string
        //_encryptionKey = md5(_encryptionKey);
        //System.out.println("key="+_encryptionKey);
        var len = _encryptionKey.toByteArray(charset("UTF-8")).size // length of the key	provided
        if (_encryptionKey.toByteArray(charset("UTF-8")).size > _key.size) len = _key.size
        var ivlen = _initVector.toByteArray(charset("UTF-8")).size
        if (_initVector.toByteArray(charset("UTF-8")).size > _iv.size) ivlen = _iv.size
        System.arraycopy(_encryptionKey.toByteArray(charset("UTF-8")), 0, _key, 0, len)
        System.arraycopy(_initVector.toByteArray(charset("UTF-8")), 0, _iv, 0, ivlen)
        //KeyGenerator _keyGen = KeyGenerator.getInstance("AES");
        //_keyGen.init(128);
        val keySpec = SecretKeySpec(_key, "AES") // Create a new SecretKeySpec
        // for the
        // specified key
        // data and
        // algorithm
        // name.
        val ivSpec = IvParameterSpec(_iv) // Create a new
        // IvParameterSpec
        // instance with the
        // bytes from the
        // specified buffer
        // iv used as
        // initialization
        // vector.

        // encryption
        if (_mode == EncryptMode.ENCRYPT) {
            // Potentially insecure random numbers on Android 4.3 and older.
            // Read
            // https://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html
            // for more info.
            _cxE!!.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec) // Initialize this cipher instance
            val results = _cxE!!.doFinal(_inputText.toByteArray(charset("UTF-8"))) // Finish
            // multi-part
            // transformation
            // (encryption)
            _out = Base64.encodeToString(results, Base64.NO_WRAP) // ciphertext
            // output
        }

        // decryption
        if (_mode == EncryptMode.DECRYPT) {
            _cxD!!.init(Cipher.DECRYPT_MODE, keySpec, ivSpec) // Initialize this ipher instance
            val decodedValue: ByteArray = Base64.decode(
                _inputText.toByteArray(),
                Base64.NO_WRAP
            )
            val decryptedVal = _cxD!!.doFinal(decodedValue) // Finish
            // multi-part
            // transformation
            // (decryption)
            _out = String(decryptedVal)
        }
        //System.out.println(_mode.equals(EncryptMode.DECRYPT)?"DECRYPT":"ENCRYPT"+": " + _out);
        return _out // return encrypted/decrypted string
    }
}