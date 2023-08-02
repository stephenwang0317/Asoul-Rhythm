package com.example.bellavoice.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongBean @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var uri: String = "",
    var song: String = "", //歌名
    var singer: String = "", //歌手名称
    val duration: String = "", //时常
    val path: String = "",
    var exist: Boolean = true
)
