package com.vas.week4.feature_chat_screen.data.model

import java.util.*

data class Message(
    val message: String,
    val time: Date,
    val myMessage: Boolean
)
