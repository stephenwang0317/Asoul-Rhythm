package com.example.bellavoice.viewmodel

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.bellavoice.model.UrlResult
import com.example.bellavoice.service.UrlService
import java.io.File

class DownloadViewModel {
    var loading by mutableStateOf(false)
        private set

    var text by mutableStateOf("")

    var result by mutableStateOf(UrlResult())

    private val urlService = UrlService.instance()

    suspend fun getUrl() {
        loading = true

        val tmp = urlService.getUrl(text)

        Log.i(
            "=====================",
            tmp.toString()
        )
        result = tmp

        loading = false
    }

    fun downloadPdf(baseActivity: Context): Long {
        if(result.data == null)
            return 0
//        val extension = url?.substring(url.lastIndexOf("."))
        val extension = ".mp3"

        val downloadReference: Long
        val dm: DownloadManager =
            baseActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(result.data!!.url)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_MUSIC,
            "/test/" + System.currentTimeMillis() + extension
        )

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(result.data!!.video_title)
        request
            .addRequestHeader("Referer", "https://www.bilibili.com/")
            .addRequestHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36"
            )
        Toast.makeText(baseActivity, "start Downloading..", Toast.LENGTH_SHORT).show()

        downloadReference = dm?.enqueue(request) ?: 0

//        loading = false
        return downloadReference

    }
}