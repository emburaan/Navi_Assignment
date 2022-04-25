package com.sumit.navi.api

import com.sumit.navi.models.Pull
import retrofit2.Response

interface ApiHelper {
    suspend fun getPulls(): Response<List<Pull>>
}
