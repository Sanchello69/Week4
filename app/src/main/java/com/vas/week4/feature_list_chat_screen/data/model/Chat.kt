package com.vas.week4.feature_list_chat_screen.data.model

import com.vas.week4.feature_chat_screen.data.model.Message
import java.util.*

data class Chat(

    val id: Int,

    val photo: Int,

    val name: String,

    val lastMessage: String,

    val time: Date,

    val unreadMessages: Int,

    val myMessage: Boolean,

    //val messageList: ArrayList<Message>

)
