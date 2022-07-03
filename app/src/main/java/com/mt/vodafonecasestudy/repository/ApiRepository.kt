package com.mt.vodafonecasestudy.repository

import com.mt.vodafonecasestudy.api.ApiService
import retrofit2.http.Url
import javax.inject.Inject

class ApiRepository
@Inject constructor(private val apiService: ApiService) {

    suspend fun getRepository() = apiService.getRepository()

    suspend fun getUser(@Url url:String) = apiService.getUser(url)

    suspend fun getRepos(@Url url:String) = apiService.getRepos(url)

}