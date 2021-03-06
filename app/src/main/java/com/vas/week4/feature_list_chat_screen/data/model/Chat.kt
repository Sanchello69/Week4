package com.vas.week4.feature_list_chat_screen.data.model

import java.util.*

data class Chat(

    val id: Int,

    val photo: Int,

    val name: String,

    val lastMessage: String,

    val time: Date,

    val unreadMessages: Int,

    val myMessage: Boolean

)
