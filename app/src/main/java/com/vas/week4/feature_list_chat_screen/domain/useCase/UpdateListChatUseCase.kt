package com.vas.week4.feature_list_chat_screen.domain.useCase

import com.vas.week4.feature_list_chat_screen.data.model.Chat
import com.vas.week4.feature_list_chat_screen.domain.repository.ListChatRepository

class UpdateListChatUseCase(private val listChatRepository: ListChatRepository) {

    fun update() {
        listChatRepository.updateChats()
    }

}