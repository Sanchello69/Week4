package com.vas.week4.feature_chat_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.week4.feature_chat_screen.domain.useCase.GetMessagesUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.GetPageMessageUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.UpdateMessagesUseCase

class ChatViewModelFactory(val getMessagesUseCase: GetMessagesUseCase,
                           val getPageMessageUseCase: GetPageMessageUseCase,
                           val updateMessagesUseCase: UpdateMessagesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(
                getMessagesUseCase = getMessagesUseCase,
                getPageMessageUseCase = getPageMessageUseCase,
                updateMessagesUseCase = updateMessagesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}