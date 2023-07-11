package com.example.bellavoice.viewmodel

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import com.example.bellavoice.model.SongBean
import java.io.File

class SongsViewModel {
    private var mData: MutableList<SongBean> = ArrayList()

    @SuppressLint("Range")
    suspend fun loadLocalSongs() {

        var path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        path = path + File.separator + "SongsManager"
        val baseFile = File(path)

        if (baseFile.isDirectory) {
            baseFile.list()

            Log.i("=========", "yes")
            for (file in baseFile.listFiles()!!) {
                Log.i("fileName:", file.name)
            }
        }
    }
}