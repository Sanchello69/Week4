package com.vas.week4.feature_chat_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.useCase.GetMessagesUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.GetPageMessageUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.UpdateMessagesUseCase
import com.vas.week4.feature_list_chat_screen.data.model.Chat
import kotlinx.coroutines.*

class ChatViewModel(private val getMessagesUseCase: GetMessagesUseCase,
                    private val getPageMessageUseCase: GetPageMessageUseCase,
                    private val updateMessagesUseCase: UpdateMessagesUseCase) : ViewModel() {

    private var listMessage: List<Message> = listOf()

    private val _messageList = MutableLiveData<List<Message>>()
    val messageList: LiveData<List<Message>>
        get() = _messageList

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private suspend fun getPageMessageUseCaseExecuting(): List<Message>{
        return withContext(Dispatchers.IO){
            getPageMessageUseCase.execute()
        }
    }

    fun getPageMessage(){
        ioScope.launch {
            listMessage = listMessage + getPageMessageUseCaseExecuting()
            //.sortedByDescending {
            //    it.time
            //}
            _messageList.value = listMessage
        }
    }

    private suspend fun updateMessagesUseCaseExecuting(): List<Message>{
        return withContext(Dispatchers.IO){
            Log.d("viewModelUpdateEx", "tuck")
            updateMessagesUseCase.update()
        }
    }

    fun updateMessages(){
        ioScope.launch {
            Log.d("viewModelUpdate", "tuck")
            listMessage = listMessage + updateMessagesUseCaseExecuting()
            _messageList.value = updateMessagesUseCaseExecuting()
        }
    }

    private suspend fun getMessagesUseCaseExecuting(lastMessage: String, lastTime: String, myMessage: Boolean,
                                                    unreadMessage: Int){
        withContext(Dispatchers.IO){
            Log.d("viewModelGetMessageEx", "tuck")
            getMessagesUseCase.execute(lastMessage, lastTime, myMessage, unreadMessage)
        }
    }

    fun getMessages(lastMessage: String, lastTime: String, myMessage: Boolean,
                    unreadMessage: Int){
        ioScope.launch {
            Log.d("viewModelGetMessage", "tuck")
            getMessagesUseCaseExecuting(lastMessage, lastTime, myMessage, unreadMessage)
//            _messageList.value = getMessagesUseCaseExecuting(
//                lastMessage, lastTime, myMessage, unreadMessage)
        }
    }

}