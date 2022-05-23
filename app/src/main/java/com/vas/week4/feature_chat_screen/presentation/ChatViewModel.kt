package com.vas.week4.feature_chat_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.useCase.GetMessagesUseCase
import kotlinx.coroutines.*

class ChatViewModel(private val getMessagesUseCase: GetMessagesUseCase) : ViewModel() {

    private val _messageList = MutableLiveData<List<Message>>()
    val messageList: LiveData<List<Message>>
        get() = _messageList

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun getMessagesUseCaseExecuting(lastMessage: String, lastTime: String, myMessage: Boolean,
                                                    unreadMessage: Int): List<Message>{
        return withContext(Dispatchers.IO){
            getMessagesUseCase.execute(lastMessage, lastTime, myMessage, unreadMessage)
        }
    }

    fun getMessages(lastMessage: String, lastTime: String, myMessage: Boolean,
                    unreadMessage: Int){
        ioScope.launch {
            _messageList.value = getMessagesUseCaseExecuting(
                lastMessage, lastTime, myMessage, unreadMessage)
        }
    }

}