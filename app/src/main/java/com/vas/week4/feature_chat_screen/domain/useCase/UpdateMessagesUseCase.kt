package com.vas.week4.feature_chat_screen.domain.useCase

import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.repository.ChatRepository

class UpdateMessagesUseCase(private val chatRepository: ChatRepository) {
    suspend fun update(): List<Message> {
        return chatRepository.updateMessages()
    }
}