package com.vas.week4.feature_list_chat_screen.domain.repository

import com.vas.week4.feature_list_chat_screen.data.model.Chat

interface ListChatRepository {
    fun getChats(): List<Chat>
}