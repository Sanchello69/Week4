package com.vas.week4.feature_list_chat_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase
import com.vas.week4.utils.Resource
import kotlinx.coroutines.Dispatchers

class ListChatViewModel(private val getListChatUseCase: GetListChatUseCase) : ViewModel() {

    fun getListChat() = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try{
            emit(Resource.Success(data = getListChatUseCase.execute()))
        } catch(e: Exception){
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}