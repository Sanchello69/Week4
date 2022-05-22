package com.vas.week4.utils

import com.vas.week4.R
import com.vas.week4.feature_list_chat_screen.data.model.Chat
import kotlin.collections.ArrayList
import com.cesarferreira.tempo.*
import com.vas.week4.feature_chat_screen.data.model.Message
import com.vas.week4.utils.GenerationChats.message
import com.vas.week4.utils.GenerationChats.nameList
import com.vas.week4.utils.GenerationChats.photoList
import com.vas.week4.utils.GenerationChats.surnameList

fun generationListChat(listChat: ArrayList<Chat>): ArrayList<Chat>{
    for (i in 0..(1..10).random()){
        listChat.add(
            Chat(
                id = listChat.size,
                photo = photoList[photoList.indices.random()],
                name = nameList[nameList.indices.random()] + " " + surnameList[surnameList.indices.random()],
                lastMessage = message[message.indices.random()],
                time = Tempo.now - (1..1000).random().minute,
                unreadMessages = (0..9).random(),
                myMessage = (0..1).random() != 0
                )
        )
    }

    val randomNumber = listChat.indices.random()
    listChat[randomNumber] = modificationChat(listChat[randomNumber])

    /*listChat.sortByDescending {
        it.time
    }*/

    return listChat
}

fun pagingListChat(page: Int, listChat: ArrayList<Chat>): List<Chat>{
    return when {
        listChat.size>=page*10 -> listChat.slice(((page-1)*10) until page*10)
        listChat.size>=(page-1)*10 -> listChat.slice(((page-1)*10) until listChat.size)
        else -> listOf()
    }
}

fun modificationChat(chat: Chat): Chat{
    return Chat(
        id = chat.id,
        photo = chat.photo,
        name = chat.name,
        lastMessage = message[message.indices.random()],
        time = Tempo.now - (1..1000).random().minute,
        unreadMessages = (0..9).random(),
        myMessage = (0..1).random() != 0
    )
}

fun generationChat(): ArrayList<Message>{
    var listMessage: ArrayList<Message> = arrayListOf()
    for (i in 0..(2..10).random()){
        listMessage.add(
            Message(
                message = message[message.indices.random()],
                time = Tempo.now - (1000-i).minute,
                myMessage = (0..1).random() != 0
            )
        )
    }
    return listMessage
}

object GenerationChats {
    val nameList = listOf(
        "Alex", "Vladimir", "Peter", "Kathryn", "David", "John", "Richard", "James", "Sandra",
        "Linda", "James", "Wendy", "Michelle", "Jesse", "Mary", "Kimberly")

    val surnameList = listOf(
        "Smith", "Obrien", "Johnson", "Davis", "Chapman", "Howell", "Roberts", "Garza", "Kelly",
        "Mack", "Harrison", "Hall", "Woods", "Kennedy", "Perez", "Ortiz", "Ellis", "Allen")


    val photoList = listOf(
        R.drawable.ph1, R.drawable.ph2, R.drawable.ph3, R.drawable.ph4,  R.drawable.ph5, R.drawable.ph6,
        R.drawable.ph7, R.drawable.ph8, R.drawable.ph9, R.drawable.ph10, R.drawable.ph11, R.drawable.ph12,
        R.drawable.ph13, R.drawable.ph14, R.drawable.ph15, R.drawable.ph16, R.drawable.ph17, R.drawable.ph18,
        R.drawable.ph19, R.drawable.ph20, R.drawable.ph21, R.drawable.ph22, R.drawable.ph23, R.drawable.ph24,
        R.drawable.ph25, R.drawable.ph26, R.drawable.ph27, R.drawable.ph28, R.drawable.ph29, R.drawable.ph30,
        R.drawable.ph31, R.drawable.ph32, R.drawable.ph33, R.drawable.ph34, R.drawable.ph35, R.drawable.ph36,
        R.drawable.ph37, R.drawable.ph38, R.drawable.ph39, R.drawable.ph40)

    val message = listOf(
        "Привет!", "Го в доту", "Что делаиш?))))", "О даааааа", "О нет", "Камон", "Норм", "Пока", "Эх",
        "Ахахвапхавхарпахвх!!!!", "Вот это смешнявка)))))", "Ахпхавхаавх харашо!!!", "Лол", "Мдя",
        "Заходит однажды в бар улитка и говорит:\n" +
                "-Можно виски с колой?\n" +
                "- Простите, но мы не обслуживаем улиток.\n" +
                "И бармен вышвырнул ее за дверь.\n" +
                "Через неделю заходит опять эта улитка и спрашивает:\n" +
                "-Ну и зачем ты это сделал!?",
        "– Папа, а мужчины попадают в ад?\n" +
                "– Да, сынок. Но только холостяки.\n" +
                "– А женатые?\n" +
                "– А мы уже там.",
        "Попали на необитаемый остров американец, немец и русский. Однажды прибило к острову бутылку, " +
                "открыли они ее, а оттуда - джинн: \n- Вы меня освободили, я исполню по два ваших желания!\n" +
                "- Мешок денег и домой! - сказал американец и исчез.\n" +
                "- Кружку пива и домой! - сказал немец и был таков.\n" +
                "- Хорошая была компания, ящик водки и всех обратно! - сказал русский.",
        "На чемпионате мира по плаванию наш спортсмен занял третий шкафчик.",
        "— Дорогой, скажи, меня эти джинсы не полнят?\n" +
                "— Ты не обидишься, если я скажу правду?\n" +
                "— Нет.\n" +
                "— Я сплю с твоей сестрой. ",
        "Не пойму, что мне нравится больше — собирать грибы или же просто медленно ходить по осеннему " +
                "лесу с ножом в руке.",
        "Глупая ситуация. Нашел объявление о продаже топора. И теперь я должен позвонить незнакомому " +
                "человеку и договориться о встрече, на которую я приду с деньгами, а он с топором.",
        "Жена посылает мужа-программиста в магазин:\n" +
                "— Возьми батон, а если будут яйца — возьми десять.\n" +
                "Муж в магазине:— Дайте батон… (Дают ему батон.) Спасибо… А яйца есть?\n" +
                "— Есть.\n" +
                "— Тогда дайте еще девять батонов.",
        "Маляр красит стенку в сумашедшем\n" +
                "доме . Подходит псих:\n" +
                "-Ты крепко за кисточку держишься?\n" +
                "-Да - отвечает тот.\n" +
                "-Тогда я лестницу забираю.",
        "Штирлиц сидел у окна. Из окна дуло. Штирлиц прикрыл форточку и дуло исчезло."
    )
}