package com.mt.vodafonecasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.vodafonecasestudy.model.RepositoriesItem
import com.mt.vodafonecasestudy.model.RepositoriesResponse
import com.mt.vodafonecasestudy.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel
@Inject constructor(private val repository: RepoRepository): ViewModel(){
    private val _response= MutableLiveData<List<RepositoriesItem>>()
    val repoResponse:LiveData<List<RepositoriesItem>>
    get()=_response

    init{
        getRepository()
    }
    private  fun getRepository()= viewModelScope.launch {
        repository.getRepository().let { response->
            if(response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("Response","getRepository: ${response.code()}")
            }
        }
    }
}