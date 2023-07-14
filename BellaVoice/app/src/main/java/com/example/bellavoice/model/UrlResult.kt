package com.example.bellavoice.model

data class UrlResult(
    val `data`: Data = Data(),
    val message: String = "",
    val status: Int = -1,
    val timestamp: Double = 0.0
)