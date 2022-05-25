package com.vas.week4.di

import com.vas.week4.feature_chat_screen.domain.repository.ChatRepository
import com.vas.week4.feature_chat_screen.domain.useCase.GetMessagesUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.GetPageMessageUseCase
import com.vas.week4.feature_chat_screen.domain.useCase.UpdateMessagesUseCase
import com.vas.week4.feature_list_chat_screen.domain.repository.ListChatRepository
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetPageListChatUseCase
import com.vas.week4.feature_list_chat_screen.domain.useCase.UpdateListChatUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetListChatUseCase(listChatRepository: ListChatRepository): GetListChatUseCase{
        return GetListChatUseCase(listChatRepository = listChatRepository)
    }

    @Provides
    fun provideGetPageListChatUseCase(listChatRepository: ListChatRepository): GetPageListChatUseCase{
        return GetPageListChatUseCase(listChatRepository = listChatRepository)
    }

    @Provides
    fun provideUpdateListChatUseCase(listChatRepository: ListChatRepository): UpdateListChatUseCase {
        return UpdateListChatUseCase(listChatRepository = listChatRepository)
    }

    @Provides
    fun provideGetMessagesUseCase(chatRepository: ChatRepository): GetMessagesUseCase{
        return GetMessagesUseCase(chatRepository = chatRepository)
    }

    @Provides
    fun provideGetPageMessageUseCase(chatRepository: ChatRepository): GetPageMessageUseCase{
        return GetPageMessageUseCase(chatRepository = chatRepository)
    }

    @Provides
    fun provideUpdateMessagesUseCase(chatRepository: ChatRepository): UpdateMessagesUseCase{
        return UpdateMessagesUseCase(chatRepository = chatRepository)
    }
}