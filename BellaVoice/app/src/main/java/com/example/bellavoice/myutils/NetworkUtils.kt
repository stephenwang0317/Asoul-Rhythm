package com.example.bellavoice.myutils

import okhttp3.OkHttpClient
import okhttp3.Request


fun ShortUrltoLong(shortUrl: String): String {
    val okHttpClient = OkHttpClient().newBuilder().followRedirects(false).build()
    val request = Request.Builder()
        .url(shortUrl)
        .build()
    val response = okHttpClient.newCall(request).execute()
    val result = response.body?.string() ?: ""
    if (result.isNotEmpty()) {
        val regex = Regex("BV[^?]*")
        return regex.find(result)?.value ?: ""
    }
    return ""
}