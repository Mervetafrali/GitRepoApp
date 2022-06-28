package com.mt.vodafonecasestudy.repository

import com.mt.vodafonecasestudy.api.ApiService
import javax.inject.Inject

class RepoRepository
@Inject constructor(private val apiService: ApiService){
    suspend fun getRepository()=apiService.getRepository()
}