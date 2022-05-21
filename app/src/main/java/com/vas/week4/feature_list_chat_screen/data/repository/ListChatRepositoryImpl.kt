package com.vas.week4.feature_list_chat_screen.data.repository

import com.vas.week4.feature_list_chat_screen.data.model.Chat
import com.vas.week4.feature_list_chat_screen.domain.repository.ListChatRepository
import com.vas.week4.utils.generationListChat

class ListChatRepositoryImpl : ListChatRepository {

    var listChat: ArrayList<Chat> = arrayListOf()
        get() = generationListChat(field)

    override fun getChats(): ArrayList<Chat> = listChat

}