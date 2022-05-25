package com.vas.week4.feature_chat_screen.domain.useCase

import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.repository.ChatRepository

class GetMessagesUseCase(private val chatRepository: ChatRepository) {
    suspend fun execute(lastMessage: String, lastTime: String, myMessage: Boolean,
                unreadMessage: Int): List<Message> {
        return chatRepository.getMessages(lastMessage, lastTime, myMessage, unreadMessage)
    }
}