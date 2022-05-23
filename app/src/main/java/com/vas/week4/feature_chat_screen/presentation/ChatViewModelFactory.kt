package com.vas.week4.feature_chat_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.week4.feature_chat_screen.domain.useCase.GetMessagesUseCase

class ChatViewModelFactory(val getMessagesUseCase: GetMessagesUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(
                getMessagesUseCase = getMessagesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}