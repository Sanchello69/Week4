package com.vas.week4.feature_list_chat_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase

class ListChatViewModelFactory(val getListChatUseCase: GetListChatUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListChatViewModel::class.java)) {
            return ListChatViewModel(
                getListChatUseCase = getListChatUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}