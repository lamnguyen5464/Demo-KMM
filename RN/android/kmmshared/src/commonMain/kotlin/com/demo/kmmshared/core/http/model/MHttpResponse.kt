package com.demo.kmmshared.core.http

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.date.*
import io.ktor.utils.io.*
import kotlin.coroutines.CoroutineContext

typealias MHttpResponse = HttpResponse

fun MHttpResponse.isSuccessfully(status: Int): Boolean {
    return status in 200..299
}

//class MHttpResponse(
//    override val call: HttpClientCall,
//    override val content: ByteReadChannel,
//    override val coroutineContext: CoroutineContext,
//    override val headers: Headers,
//    override val requestTime: GMTDate,
//    override val responseTime: GMTDate,
//    override val status: HttpStatusCode,
//    override val version: HttpProtocolVersion
//) : HttpResponse() {
//
//    private fun isSuccessfully(status: Int): Boolean {
//        return status in 200..299
//    }
//}