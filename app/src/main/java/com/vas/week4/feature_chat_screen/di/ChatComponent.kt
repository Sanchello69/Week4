package com.vas.week4.feature_chat_screen.di

import androidx.lifecycle.ViewModel
import com.vas.week4.feature_chat_screen.presentation.ChatFragment
import com.vas.week4.feature_chat_screen.presentation.ChatViewModelFactory
import dagger.Component
import kotlin.properties.Delegates

@Component(dependencies = [ChatDeps::class])
internal interface ChatComponent {
    fun inject(fragment: ChatFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: ChatDeps): Builder

        fun build(): ChatComponent
    }
}

interface ChatDeps {
    val chatViewModelFactory: ChatViewModelFactory
}

internal class ChatComponentViewModel : ViewModel(){

    val newChatComponent = DaggerChatComponent.builder().deps(ChatDepsProvider.deps).build()
}

interface ChatDepsProvider {
    val deps: ChatDeps

    companion object : ChatDepsProvider by ChatDepsStore
}

object ChatDepsStore : ChatDepsProvider {
    override var deps: ChatDeps by Delegates.notNull()
}