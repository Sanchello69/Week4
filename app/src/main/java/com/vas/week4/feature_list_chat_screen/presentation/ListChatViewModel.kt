package com.vas.week4.feature_list_chat_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vas.week4.feature_list_chat_screen.data.model.Chat
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetPageListChatUseCase
import com.vas.week4.feature_list_chat_screen.domain.useCase.UpdateListChatUseCase
import com.vas.week4.utils.Resource
import kotlinx.coroutines.*

class ListChatViewModel(private val getListChatUseCase: GetListChatUseCase,
                        private val getPageListChatUseCase: GetPageListChatUseCase,
                        private val updateListChatUseCase: UpdateListChatUseCase) : ViewModel() {

    private var listData: List<Chat> = listOf()

    private val _chatList = MutableLiveData<List<Chat>>()
    val chatList: LiveData<List<Chat>>
        get() = _chatList

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun getPageListChatUseCaseExecuting(): List<Chat>{
        return withContext(Dispatchers.IO){
            getPageListChatUseCase.execute()
        }
    }

    fun getPageListChat(){
        ioScope.launch {
            Log.d("list_size", listData.size.toString())
            listData = (listData.toSet() + getPageListChatUseCaseExecuting().toSet()).toList()
            //.sortedByDescending {
            //    it.time
            //}
            Log.d("list_size", listData.size.toString())
            _chatList.value = listData
        }
    }

    private suspend fun updateListChatUseCaseExecuting(): List<Chat>{
        return withContext(Dispatchers.IO){
            updateListChatUseCase.update()
        }
    }

    fun updateListChat(){
        ioScope.launch {
            _chatList.value = updateListChatUseCaseExecuting()
        }
    }
}