package com.vas.week4.feature_chat_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vas.week4.databinding.FragmentChatBinding
import com.vas.week4.feature_chat_screen.di.ChatComponentViewModel
import javax.inject.Inject

class ChatFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ChatViewModelFactory

    private var binding: FragmentChatBinding? = null
    private var viewModel: ChatViewModel? = null
    private var adapterMessages: ChatAdapter? = null
    private var scrollListener: RecyclerView.OnScrollListener? = null

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
        initSwipeRefreshLayout()
    }



    private fun setupObservers() {
        Log.d("fragmentSetupObservers", "tuck")

        viewModel?.getPageMessage()
        viewModel?.messageList?.observe(viewLifecycleOwner, Observer {
            adapterMessages?.messages = it
        })
    }

    private fun initMessagesRecyclerView() {
        //val layoutManager = LinearLayoutManager(context)
        //layoutManager.reverseLayout = true
        //layoutManager.stackFromEnd = true
        //binding?.messageRecyclerView?.layoutManager = layoutManager
        adapterMessages = ChatAdapter(requireContext())
        binding?.messageRecyclerView?.adapter = adapterMessages

        setRecyclerViewScrollListener()
    }

    private fun initSwipeRefreshLayout() {
        Log.d("fragmentUpd", "tuck")
        binding?.swipeRefreshLayoutMessage?.setOnRefreshListener {
            Log.d("fragmentUpd", "tuck")
            viewModel?.updateMessages()
            binding?.swipeRefreshLayoutMessage?.isRefreshing = false
        }
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount ==  adapterMessages?.messages?.size) {
                    Log.d("fragmentScrollListener", "tuck")
                    viewModel?.getPageMessage()
                    //binding?.messageRecyclerView?.removeOnScrollListener(scrollListener!!)
                }
            }
        }
        binding?.messageRecyclerView?.addOnScrollListener(scrollListener as RecyclerView.OnScrollListener)
    }
}