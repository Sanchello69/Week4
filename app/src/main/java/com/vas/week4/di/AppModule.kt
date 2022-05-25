package com.vas.week4.di

import com.vas.week4.feature_chat_screen.domain.useCase.GetMessagesUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.GetPageMessageUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.UpdateMessagesUseCase
import com.vas.week4.feature_chat_screen.presentation.ChatViewModelFactory
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetPageListChatUseCase
import com.vas.week4.feature_list_chat_screen.domain.useCase.UpdateListChatUseCase
import com.vas.week4.feature_list_chat_screen.presentation.ListChatViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideListChatViewModelFactory(getListChatUseCase: GetListChatUseCase,
                                        getPageListChatUseCase: GetPageListChatUseCase,
                                        updateListChatUseCase: UpdateListChatUseCase
    ): ListChatViewModelFactory{
        return ListChatViewModelFactory(getListChatUseCase = getListChatUseCase,
        getPageListChatUseCase = getPageListChatUseCase,
        updateListChatUseCase = updateListChatUseCase)
    }

    @Provides
    fun provideChatViewModelFactory(getMessagesUseCase: GetMessagesUseCase,
                                    getPageMessageUseCase: GetPageMessageUseCase,
                                    updateMessagesUseCase: UpdateMessagesUseCase): ChatViewModelFactory{
        return ChatViewModelFactory(getMessagesUseCase = getMessagesUseCase,
                                    getPageMessageUseCase = getPageMessageUseCase,
                                    updateMessagesUseCase = updateMessagesUseCase)
    }
}