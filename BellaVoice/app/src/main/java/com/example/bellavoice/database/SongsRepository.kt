package com.example.bellavoice.database

import com.example.bellavoice.model.SongBean
import kotlinx.coroutines.flow.Flow

class SongsRepository(private val songsDao: SongsDao) {

    fun getAllOrderById(): Flow<List<SongBean>> {
        return songsDao.getAllOrderById()
    }

    fun getAllOrderBySinger(): Flow<List<SongBean>> {
        return songsDao.getAllOrderBySinger()
    }

    fun getAllOrderBySong(): Flow<List<SongBean>> {
        return songsDao.getAllOrderBySong()
    }

    suspend fun addSong(item: SongBean) {
        songsDao.insert(item)
    }

    suspend fun deleteAll() {
        songsDao.deleteAll()
    }

    suspend fun deleteSong(item: SongBean) {
        songsDao.delete(item)
    }

    suspend fun checkIfExist(path: String): Int {
        return songsDao.checkIfExist(path)
    }

    suspend fun getSongById(id: Int): SongBean {
        return songsDao.getSongById(id)
    }

    suspend fun upsertSong(song: SongBean) {
        songsDao.upsertSong(song)
    }
}