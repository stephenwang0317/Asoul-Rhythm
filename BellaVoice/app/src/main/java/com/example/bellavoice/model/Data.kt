package com.example.bellavoice.model

import java.util.Collections

data class Data(
    val urls: List<Url> = Collections.emptyList(),
    val video_title: String = "",
    val img: String = ""
)