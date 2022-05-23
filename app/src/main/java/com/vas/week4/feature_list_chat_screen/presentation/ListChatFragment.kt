package com.vas.week4.feature_list_chat_screen.presentation

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vas.week4.R
import com.vas.week4.databinding.FragmentListChatBinding
import com.vas.week4.feature_list_chat_screen.data.model.Chat
import com.vas.week4.feature_list_chat_screen.di.ListChatComponentViewModel
import javax.inject.Inject

class ListChatFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ListChatViewModelFactory

    private var binding: FragmentListChatBinding? = null
    private var viewModel: ListChatViewModel? = null
    private var adapterChats: ListChatAdapter? = null
    private var scrollListener: RecyclerView.OnScrollListener? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ListChatComponentViewModel>()
            .newListChatComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListChatBinding.inflate(inflater, container, false)

        setupViewModel()
        setupUI()
        setupObserversForPaging()

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ListChatViewModel::class.java)
    }

    private fun setupUI() {
        initChatsRecyclerView()
        initSwipeRefreshLayout()
    }

    private fun setupObserversForPaging() {
        viewModel?.getPageListChat()
        viewModel?.chatList?.observe(viewLifecycleOwner, Observer {
            adapterChats?.differ?.submitList(it)
            binding?.swipeRefreshLayout?.isRefreshing = false
        })

    }

    private fun initChatsRecyclerView() {
        adapterChats = ListChatAdapter()
        adapterChats?.onClickListener = object : ListChatAdapter.OnChatClickListener{
            override fun onChatClick(chat: Chat) {
                navigateToChat(chat)
            }
        }
        binding?.chatRecyclerView?.adapter = adapterChats
        binding?.chatRecyclerView?.itemAnimator = null
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount ==  adapterChats?.differ?.currentList?.size) {
                    viewModel?.getPageListChat()
                    //binding?.chatRecyclerView?.removeOnScrollListener(scrollListener!!)
                }
            }
        }
        binding?.chatRecyclerView?.addOnScrollListener(scrollListener as RecyclerView.OnScrollListener)
    }

    private fun navigateToChat(item: Chat){
        Log.d("click", "click")
        val navController = findNavController()
        val bundle = Bundle().apply {
            putInt("photo", item.photo)
            putString("name", item.name)
            putString("lastMessage", item.lastMessage)
            putString("time", item.time.toString())
            putInt("unreadMessage", item.unreadMessages)
            putBoolean("myMessage", item.myMessage)
        }
        navController.navigate(R.id.action_listChatFragment_to_chatFragment, bundle)
    }

    private fun initSwipeRefreshLayout() {
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            viewModel?.updateListChat()
        }
    }
}