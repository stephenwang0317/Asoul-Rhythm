package com.example.bellavoice.viewmodel

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bellavoice.model.SongBean
import java.io.File

class SongsViewModel() : ViewModel() {
    private var mData: MutableList<SongBean> = ArrayList()
    private val _targetSong = MutableLiveData<MutableList<SongBean>>()
    private var _currentSongId = -1

    var isPlaying by mutableStateOf(false)
        private set
    val targetSong: MutableLiveData<MutableList<SongBean>>
        get() = _targetSong

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    fun searchSong(targetSong: String = "") {
        if (targetSong != "") {

        } else {
            _targetSong.postValue(mData)
        }
    }

    @SuppressLint("Range")
    fun loadLocalSongs() {

        var path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        path = path + File.separator + "SongsManager"
        val baseFile = File(path)
        var id = 0
        if (baseFile.isDirectory) {
            for (file in baseFile.listFiles()!!) {
                val item = SongBean(
                    song = file.name,
                    path = file.path,
                    id = id
                )
                id++
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
        if (next == mData.size) {
            next = 0
        }
        playMusic(mData[_currentSongId + 1])
        _currentSongId = next
    }

    fun previousSong() {
        mediaPlayer.stop()
        var prev = _currentSongId - 1
        if (prev == -1) {
            prev = mData.size -1
        }
        playMusic(mData[prev])
        _currentSongId = prev
    }

    init {
        loadLocalSongs()
        searchSong()
    }

    override fun onCleared() {
        mediaPlayer.release()
        super.onCleared()
    }
}