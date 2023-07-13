package com.example.bellavoice.viewmodel

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.bellavoice.model.SongBean
import java.io.File

class SongsViewModel {
    private var mData: MutableList<SongBean> = ArrayList()
    private val _targetSong = MutableLiveData<MutableList<SongBean>>()

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

        if (baseFile.isDirectory) {
            baseFile.list()

            Log.i("=========", "yes")
            for (file in baseFile.listFiles()!!) {
                Log.i("fileName:", file.name)
                val item = SongBean(
                    song = file.name,
                    path = file.path
                )
                mData.add(item)
            }
        }
    }

    fun playMusic(bean: SongBean) {
        mediaPlayer.apply {
            reset()
            setDataSource(bean.path)
            prepare()
            start()
        }
        isPlaying = true
    }

    fun stopPlay() {
        mediaPlayer.pause()
        isPlaying = false
    }

    fun resumePlay() {
        mediaPlayer.start()
        isPlaying = true
    }
    init {
        loadLocalSongs()
        searchSong()
    }
}