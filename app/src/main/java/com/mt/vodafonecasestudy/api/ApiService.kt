package com.mt.vodafonecasestudy.api

import com.mt.vodafonecasestudy.helper.Constants.END_POINT_REPO
import com.mt.vodafonecasestudy.helper.Constants.END_POINT_REPOS
import com.mt.vodafonecasestudy.helper.Constants.END_POINT_USERS
import com.mt.vodafonecasestudy.model.Repos
import com.mt.vodafonecasestudy.model.RepositoriesResponse
import com.mt.vodafonecasestudy.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {


    @GET(END_POINT_REPO)
    suspend fun getRepository(): Response<RepositoriesResponse>
    @GET(END_POINT_USERS)
    suspend fun getUser():Response<Users>
    @GET(END_POINT_REPOS)
    suspend fun getRepos():Response<Repos>
}