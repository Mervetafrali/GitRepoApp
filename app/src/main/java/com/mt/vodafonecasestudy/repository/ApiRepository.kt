package com.mt.vodafonecasestudy.repository

import com.mt.vodafonecasestudy.api.ApiService
import retrofit2.http.Url
import javax.inject.Inject

class ApiRepository

@Inject constructor(private val apiService: ApiService){

    suspend fun getRepository()=apiService.getRepository()

    suspend fun getUser()=apiService.getUser()

    suspend fun getRepos()=apiService.getRepos()
}