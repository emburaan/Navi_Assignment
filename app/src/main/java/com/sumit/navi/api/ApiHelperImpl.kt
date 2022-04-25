package com.sumit.navi.api

import com.sumit.navi.models.Pull
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiServices: ApiServices
) : ApiHelper {
    override suspend fun getPulls(): Response<List<Pull>> = apiServices.getPulls()
}