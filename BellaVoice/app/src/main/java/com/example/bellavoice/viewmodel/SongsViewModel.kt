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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bellavoice.database.SongsDatabase
import com.example.bellavoice.database.SongsRepository
import com.example.bellavoice.model.SongBean
import java.io.File

class SongsViewModel(context: Context) : ViewModel() {
    private var mData: MutableList<SongBean> = ArrayList()
    val targetSong = mutableStateListOf<SongBean>()
    private var _currentSongId = -1

    private val songsRepository: SongsRepository

    var isPlaying by mutableStateOf(false)
        private set

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    init {
        val songsDao = SongsDatabase.getInstance(context).songsDao()
        songsRepository = SongsRepository(songsDao)
//        loadLocalSongs()
//        searchSong()
    }

    fun searchSong(targetSong: String = "") {
        if (targetSong != "") {

        } else {
            this.targetSong.clear()
            Log.d("==============", mData.size.toString())
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
        _currentSongId = bean.id
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

    fun getCurrent(): Int {
        return _currentSongId
    }

    fun deleteFile(context: Context, bean: SongBean): Boolean {
        val resolver = context.contentResolver
        val musicFile = File(bean.path)
        return true
    }

    fun release() {
        mediaPlayer.release()
    }
}