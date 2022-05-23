package com.vas.week4.di

import com.vas.week4.MainActivity
import com.vas.week4.feature_chat_screen.di.ChatDeps
import com.vas.week4.feature_list_chat_screen.di.ListChatDeps
import com.vas.week4.feature_list_chat_screen.presentation.ListChatViewModelFactory
import dagger.Component

@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class])
interface AppComponent : ListChatDeps, ChatDeps {

    override val listChatViewModelFactory: ListChatViewModelFactory

    fun inject(mainActivity: MainActivity)

}