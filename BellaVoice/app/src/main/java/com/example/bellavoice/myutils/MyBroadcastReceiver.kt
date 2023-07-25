package com.example.bellavoice.myutils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bellavoice.database.SongsDatabase
import com.example.bellavoice.database.SongsRepository
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.viewmodel.DownloadViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyBroadcastReceiver(private val downloadViewModel: DownloadViewModel) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            DownloadManager.ACTION_DOWNLOAD_COMPLETE -> {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                Log.d("====================", "receive DOWNLOAD_COMPLETE, id: $id")
                val map = downloadViewModel.downloadMap
                val result = map.getOrDefault(id, null)
                if (result == null) {
                    return
                } else {
                    val songsDao = context?.let { SongsDatabase.getInstance(it).songsDao() }
                    val songsRepository = songsDao?.let { SongsRepository(it) }
                    songsRepository.let {
                        GlobalScope.launch {
                            songsRepository?.addSong(
                                SongBean(
                                    id = null,
                                    uri = result.info.data?.img ?: "",
                                    path = result.path,
                                    song = result.path.substringAfterLast('/')
                                        .replace("(\\.[^.]+)\$".toRegex(), "")
                                )
                            )
                            map.remove(id)
                        }
                    }
                }
            }
        }
    }
}