package com.vas.week4.feature_chat_screen.domain.repository

import com.vas.week4.feature_chat_screen.data.model.Message

interface ChatRepository {
    suspend fun getMessages(
        lastMessage: String, lastTime: String, myMessage: Boolean,
        unreadMessage: Int
    ): List<Message>

    suspend fun updateMessages(): List<Message>

    suspend fun getPageMessage(): List<Message>
}