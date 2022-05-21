package com.vas.week4.di

import com.vas.week4.feature_list_chat_screen.domain.useCase.GetListChatUseCase
import com.vas.week4.feature_list_chat_screen.presentation.ListChatViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideListChatViewModelFactory(getListChatUseCase: GetListChatUseCase): ListChatViewModelFactory{
        return ListChatViewModelFactory(getListChatUseCase = getListChatUseCase)
    }
}