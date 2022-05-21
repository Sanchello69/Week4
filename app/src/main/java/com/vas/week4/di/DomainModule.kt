package com.vas.week4.di

import com.vas.week4.feature_list_chat_screen.domain.repository.ListChatRepository
import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetListChatUseCase(listChatRepository: ListChatRepository): GetListChatUseCase{
        return GetListChatUseCase(listChatRepository = listChatRepository)
    }
}