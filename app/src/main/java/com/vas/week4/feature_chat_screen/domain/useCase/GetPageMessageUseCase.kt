package com.vas.week4.feature_chat_screen.domain.useCase

import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.repository.ChatRepository

class GetPageMessageUseCase(private val chatRepository: ChatRepository) {
    suspend fun execute(): List<Message> {
        return chatRepository.getPageMessage()
    }
}