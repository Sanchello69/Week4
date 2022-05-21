package com.vas.week4.feature_list_chat_screen.di

import androidx.lifecycle.ViewModel
import com.vas.week4.feature_list_chat_screen.presentation.ListChatFragment
import com.vas.week4.feature_list_chat_screen.presentation.ListChatViewModelFactory
import dagger.Component
import kotlin.properties.Delegates.notNull

@Component(dependencies = [ListChatDeps::class])
internal interface ListChatComponent {
    fun inject(fragment: ListChatFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: ListChatDeps): Builder

        fun build(): ListChatComponent
    }
}

interface ListChatDeps {
    val listChatViewModelFactory: ListChatViewModelFactory
}

internal class ListChatComponentViewModel : ViewModel(){

    val newListChatComponent = DaggerListChatComponent.builder().deps(ListChatDepsProvider.deps).build()
}

interface ListChatDepsProvider {
    val deps: ListChatDeps

    companion object : ListChatDepsProvider by ListChatDepsStore
}

object ListChatDepsStore : ListChatDepsProvider {
    override var deps: ListChatDeps by notNull()
}