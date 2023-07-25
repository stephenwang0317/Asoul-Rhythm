package com.example.bellavoice.viewmodel

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bellavoice.model.UrlResult
import com.example.bellavoice.service.UrlService
import java.io.File

class DownloadViewModel() {
    var loading by mutableStateOf(false)
        private set

    var bv by mutableStateOf("")
    var part by mutableStateOf("")
    var fileName by mutableStateOf("")

    var result by mutableStateOf(UrlResult())

    private val urlService = UrlService.instance()

    val downloadMap: MutableMap<Long, DownloadInfo> = mutableMapOf()

    suspend fun getUrl() {
        loading = true
        val tmpPart = try {
            part.toInt()
        } catch (e: NumberFormatException) {
            1
        }
        val tmp = urlService.getUrl(bv, tmpPart)

        Log.i(
            "=====================",
            tmp.toString()
        )
        result = tmp
        if (result.data != null)
            fileName = result.data!!.video_title.replace("/", " ")
        loading = false
    }

    suspend fun getUrl(bv: String): UrlResult {
        val tmp = urlService.getUrl(bv)
        Log.i(
            "suspend fun getUrl(bv: String): UrlResult",
            tmp.toString()
        )
        return tmp

    }

    fun downloadPdf(baseActivity: Context, quality: Int = 0): Long {
        if (result.data == null)
            return 0
//        val extension = url?.substring(url.lastIndexOf("."))
        val extension = ".mp3"

        val downloadReference: Long
        val dm: DownloadManager =
            baseActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(result.data!!.urls[quality].url)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_MUSIC,
            "/SongsManager/$fileName$extension"
        )

        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        val filePath =
            path + File.separator + "SongsManager" + File.separator + "$fileName$extension"
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(result.data!!.video_title)
        request
            .addRequestHeader("Referer", "https://www.bilibili.com/")
            .addRequestHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36"
            )
        Toast.makeText(baseActivity, "start Downloading..", Toast.LENGTH_SHORT).show()

        downloadReference = dm.enqueue(request)

        downloadMap[downloadReference] = DownloadInfo(result, filePath)
//        loading = false
        return downloadReference

    }
}

data class DownloadInfo(
    val info: UrlResult = UrlResult(),
    val path: String = ""
)