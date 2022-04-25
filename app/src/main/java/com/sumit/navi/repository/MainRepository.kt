package com.sumit.navi.repository

import com.sumit.navi.api.ApiHelper
import com.sumit.navi.models.Pull
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
) {
    suspend fun getPulls(): Response<List<Pull>> {
        return apiHelper.getPulls()
    }
}