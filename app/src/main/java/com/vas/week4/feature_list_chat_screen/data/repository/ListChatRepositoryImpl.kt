package com.vas.week4.feature_list_chat_screen.data.repository

import android.util.Log
import com.vas.week4.feature_list_chat_screen.data.model.Chat
import com.vas.week4.feature_list_chat_screen.domain.repository.ListChatRepository
import com.vas.week4.utils.generationListChat
import com.vas.week4.utils.modificationChat
import com.vas.week4.utils.pagingListChat

class ListChatRepositoryImpl : ListChatRepository {

    var listChat: ArrayList<Chat> = arrayListOf()
        get() = generationListChat(field)

    var countPage = 0

    override fun getChats(): List<Chat> = listChat.toList()

    override fun updateChats(): List<Chat>{
        for (i in 0..10){
            val randomNumber = listChat.indices.random()
            listChat[randomNumber] = modificationChat(listChat[randomNumber])
        }
        countPage = 0
        return getPageChats()
    }

    override fun getPageChats(): List<Chat> {
        Log.d("list_size_repo", listChat.size.toString())
        countPage += 1
        return pagingListChat(countPage, listChat)
    }

}