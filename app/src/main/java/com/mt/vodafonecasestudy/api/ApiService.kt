package com.mt.vodafonecasestudy.api

import com.mt.vodafonecasestudy.helper.Constants.END_POINT_REPO
import com.mt.vodafonecasestudy.model.Repositories
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT_REPO)
    suspend fun getRepository(): Response<Repositories>
}