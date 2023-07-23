package com.example.bellavoice.model

import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongBean @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val uri: String = "",
    val song: String = "", //歌名
    val singer: String = "", //歌手名称
    val duration: String = "", //时常
    val path: String = ""
)
