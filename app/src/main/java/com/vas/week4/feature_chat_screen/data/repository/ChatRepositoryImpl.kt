package com.vas.week4.feature_chat_screen.data.repository

import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.repository.ChatRepository
import com.vas.week4.utils.generationChat

class ChatRepositoryImpl : ChatRepository {

    var listMessage: ArrayList<Message> = arrayListOf()

    var countPage = 0

    override fun getMessages(lastMessage: String, lastTime: String, myMessage: Boolean,
                    unreadMessage: Int): List<Message> {
        listMessage = generationChat(
            listMessage = listMessage,
            lastMessage = lastMessage,
            myMessage = myMessage,
            unreadMessage = unreadMessage,
            lastTime = lastTime
        )

        return listMessage.toList()
    }
}