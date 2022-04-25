package com.sumit.navi.api

import com.sumit.navi.models.Pull
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("pulls")
    suspend fun getPulls(): Response<List<Pull>>
}