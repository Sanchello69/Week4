package com.vas.week4.feature_list_chat_screen.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vas.week4.R
import com.vas.week4.databinding.FragmentListChatBinding
import com.vas.week4.feature_list_chat_screen.di.ListChatComponentViewModel
import com.vas.week4.utils.Resource
import javax.inject.Inject

class ListChatFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ListChatViewModelFactory

    private var binding: FragmentListChatBinding? = null
    private var viewModel: ListChatViewModel? = null
    private var adapterChats: ListChatAdapter? = null

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
        setupObservers()

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

    private fun setupObservers() {
        viewModel?.getListChat()?.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource) {
                    is Resource.Success -> {
                        adapterChats?.data = it.data!!
                        binding?.swipeRefreshLayout?.isRefreshing = false
                    }

                    is Resource.Error -> {
                        binding?.swipeRefreshLayout?.isRefreshing = false
                    }

                    is Resource.Loading -> {
                        binding?.swipeRefreshLayout?.isRefreshing = true
                    }
                }
            }
        })
    }

    private fun initChatsRecyclerView() {
        adapterChats = ListChatAdapter()
        binding?.chatRecyclerView?.adapter = adapterChats
    }

    private fun initSwipeRefreshLayout() {
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            setupObservers()
        }
    }
}