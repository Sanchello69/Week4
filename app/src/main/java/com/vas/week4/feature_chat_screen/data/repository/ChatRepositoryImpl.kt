package com.vas.week4.feature_chat_screen.data.repository

import android.util.Log
import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.feature_chat_screen.domain.repository.ChatRepository
import com.vas.week4.utils.*

class ChatRepositoryImpl : ChatRepository {

    private var listMessage: ArrayList<Message> = arrayListOf()

    private var countPage = 0

    init {
        listMessage.addAll(generationChat(
            listMessage = listMessage
        ))
    }

    override suspend fun getMessages(lastMessage: String, lastTime: String, myMessage: Boolean,
                    unreadMessage: Int): List<Message> {
        Log.d("repoGetMes", "tuck")
        listMessage.addAll(generationChat(
            listMessage = listMessage,
            lastMessage = lastMessage,
            myMessage = myMessage,
            unreadMessage = unreadMessage,
            lastTime = lastTime
        ))
        Log.d("get_repo", listMessage.size.toString())
        return listMessage.toList()
    }

    override suspend fun updateMessages(): List<Message>{
        Log.d("repoUpdMes", "tuck")
        Log.d("update_repo", listMessage.size.toString())
        for (i in 0..10){
            //val randomNumber = listMessage.indices.random()
            //listMessage[randomNumber] = modificationMessage(listMessage[randomNumber])
            listMessage.add(addMessage())
        }
        countPage = 0
        return getPageMessage()
    }

    override suspend fun getPageMessage(): List<Message>{
        Log.d("repoPageMes", "tuck")
        countPage += 1
        Log.d("repoPageMes", listMessage.size.toString())
        Log.d("repoPageMes", pagingListMessage(page = countPage, listMessage = listMessage).size.toString())
        return pagingListMessage(page = countPage, listMessage = listMessage)
    }
}