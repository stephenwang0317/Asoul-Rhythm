package com.example.bellavoice.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bellavoice.database.SongsDatabase
import com.example.bellavoice.database.SongsRepository
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.myutils.NotificationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.io.File

class SongsViewModel(context: Context) : ViewModel() {
    private var mData: MutableList<SongBean> = ArrayList()
    val targetSong = mutableStateListOf<SongBean>()
    private var _currentSongId = -1
//    var currentBean: SongBean? = null


    private val songsRepository: SongsRepository
    private val _songsState = MutableStateFlow(SongsViewState())
    val songsState: StateFlow<SongsViewState>
        get() = _songsState

    var isPlaying by mutableStateOf(false)
        private set

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    init {
        val songsDao = SongsDatabase.getInstance(context).songsDao()
        songsRepository = SongsRepository(songsDao)
        viewModelScope.launch {
            getAllById()
        }
//        notificationUtils.showMyNotification(context, mediaPlayer)
    }

    fun searchSong(targetSong: String = "") {
        if (targetSong != "") {

        } else {
            this.targetSong.clear()
            this.targetSong.addAll(mData)
        }
    }

    @SuppressLint("Range")
    fun loadLocalSongs() {
        mData.clear()
        var path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        path = path + File.separator + "SongsManager"
        val baseFile = File(path)
//        var id = 0
        if (baseFile.isDirectory) {
            for (file in baseFile.listFiles()!!) {
                val item = SongBean(
                    song = file.name.replace("(\\.[^.]+)\$".toRegex(), ""),
                    path = file.path,
                )
//                id++
                mData.add(item)
            }
        }
    }

    fun playMusic(bean: SongBean) {
        try {
            mediaPlayer.apply {
                stop()
                reset()
                setDataSource(bean.path)
                setOnCompletionListener {
                    nextSong()
                }
                prepare()
                start()
            }
        } catch (e: Exception) {
            Log.d("===========playMusic", e.stackTrace.toString())
        }
        _currentSongId = bean.id ?: 0
        isPlaying = true
    }

    fun stopPlay() {
        mediaPlayer.pause()
        isPlaying = false
    }

    fun resumePlay() {
        if (_currentSongId == -1) {

        } else {
            mediaPlayer.start()
            isPlaying = true
        }
    }

    fun nextSong() {
        mediaPlayer.stop()
        var next = _currentSongId + 1
        if (next >= mData.size) {
            next = 0
        }
        playMusic(mData[next])
        _currentSongId = next
    }

    fun previousSong() {
        mediaPlayer.stop()
        var prev = _currentSongId - 1
        if (prev < 0) {
            prev = mData.size - 1
        }
        playMusic(mData[prev])
        _currentSongId = prev
    }

    override fun onCleared() {
        mediaPlayer.release()
        super.onCleared()
    }

    fun release() {
        mediaPlayer.release()
    }

    private suspend fun scanLocalAndWriteDatabase() {
        var path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        path = path + File.separator + "SongsManager"
        val baseFile = File(path)
//        var id = 0
        if (baseFile.isDirectory) {
            for (file in baseFile.listFiles()!!) {
                val item = SongBean(
                    id = null,
                    song = file.name.replace("(\\.[^.]+)\$".toRegex(), ""),
                    path = file.path,
                )
                songsRepository.addSong(item)
            }
        }
    }

    var isRefreshing by mutableStateOf(false)
        private set

    suspend fun refreshList() {
        isRefreshing = true

        var path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        path = path + File.separator + "SongsManager"
        val baseFile = File(path)
        if (baseFile.isDirectory) {
            Log.e("listFiles.size", baseFile.listFiles()?.size.toString())
            for (file in baseFile.listFiles()!!) {
                if (songsRepository.checkIfExist(file.path) == 1) {
                    continue
                } else {
                    val item = SongBean(
                        id = null,
                        song = file.name.replace("(\\.[^.]+)\$".toRegex(), ""),
                        path = file.path,
                    )
                    songsRepository.addSong(item)
                }
            }
        }

        val beans = songsState.value.songs
        for (item in beans) {
            val tempFile = File(item.path)
            if (tempFile.exists()) {
                continue
            } else {
                item.exist = false
                songsRepository.upsertSong(item)
            }
        }
        isRefreshing = false
//        bug未修复
        getAllBySelectOrder(0)

    }

    private suspend fun getAllBySelectOrder(type: Int) {
        val flow = when (type) {
            0 -> songsRepository.getAllOrderById()
            1 -> songsRepository.getAllOrderBySinger()
            else -> songsRepository.getAllOrderBySong()
        }
        combine(flow) { flowArr ->
            SongsViewState(flowArr[0])
        }.collect { _songsState.value = it }
        return
    }

    suspend fun getAllById() {
        getAllBySelectOrder(0)
    }

    suspend fun getAllBySinger() {
        getAllBySelectOrder(1)
    }

    suspend fun getAllBySong() {
        getAllBySelectOrder(2)
    }

    suspend fun upsert(songBean: SongBean) {
        songsRepository.upsertSong(songBean)
    }

    suspend fun delete(songBean: SongBean) {
        songsRepository.deleteSong(songBean)
    }

    suspend fun getSingleSong(id: Int): SongBean {
        return songsRepository.getSongById(id)
    }
}

data class SongsViewState(
    val songs: List<SongBean> = emptyList()
)