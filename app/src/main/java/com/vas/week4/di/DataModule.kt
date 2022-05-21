package com.vas.week4.di

import com.vas.week4.feature_list_chat_screen.data.repository.ListChatRepositoryImpl
import com.vas.week4.feature_list_chat_screen.domain.repository.ListChatRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideListChatRepository(): ListChatRepository{
        return ListChatRepositoryImpl()
    }
}