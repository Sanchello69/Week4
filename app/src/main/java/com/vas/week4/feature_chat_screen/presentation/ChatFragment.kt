package com.vas.week4.feature_chat_screen.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vas.week4.databinding.FragmentChatBinding
import com.vas.week4.feature_chat_screen.di.ChatComponentViewModel
import com.vas.week4.feature_list_chat_screen.presentation.ListChatAdapter
import javax.inject.Inject

class ChatFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ChatViewModelFactory

    private var binding: FragmentChatBinding? = null
    private var viewModel: ChatViewModel? = null
    private var adapterMessages: ChatAdapter? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ChatComponentViewModel>()
            .newChatComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater, container, false)

        setupViewModel()
        setupUI()
        setupObservers()

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChatViewModel::class.java)
    }

    private fun setupUI() {
        binding?.nameTextView?.text = arguments?.getString("name")
        binding?.photo?.setImageResource(arguments?.getInt("photo")!!)

        initMessagesRecyclerView()
    }

    private fun setupObservers() {
        viewModel?.getMessages(
            lastMessage = arguments?.getString("lastMessage")!!,
            lastTime = arguments?.getString("time")!!,
            myMessage = arguments?.getBoolean("myMessage")!!,
            unreadMessage = arguments?.getInt("unreadMessage")!!
        )
        viewModel?.messageList?.observe(viewLifecycleOwner, Observer {
            adapterMessages?.messages = it
        })
    }

    private fun initMessagesRecyclerView() {
        adapterMessages = ChatAdapter(requireContext())
        binding?.messageRecyclerView?.adapter = adapterMessages
    }
}