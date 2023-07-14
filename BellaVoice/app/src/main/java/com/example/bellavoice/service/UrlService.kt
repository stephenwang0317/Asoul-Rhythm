package com.example.bellavoice.service

import com.example.bellavoice.model.UrlResult
import com.example.homework.network.Network
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UrlService {

    companion object {
        fun instance(): UrlService {
            return Network.createService(UrlService::class.java)
        }
    }

    @GET("{bv}")
    suspend fun getUrl(
        @Path("bv") bv: String,
        @Query("p") p: Int = 1
    ): UrlResult
}