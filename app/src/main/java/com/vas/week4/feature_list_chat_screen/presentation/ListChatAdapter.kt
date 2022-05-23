package com.vas.week4.feature_list_chat_screen.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cesarferreira.tempo.toString
import com.vas.week4.R
import com.vas.week4.feature_list_chat_screen.data.model.Chat

class ListChatAdapter : RecyclerView.Adapter<ListChatAdapter.ItemChatViewHolder>() {

    var onClickListener: OnChatClickListener? = null

    interface OnChatClickListener {
        fun onChatClick(chat: Chat)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChatViewHolder =
        ItemChatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        )

    override fun onBindViewHolder(holder: ItemChatViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ItemChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val photo: ImageView = itemView.findViewById(R.id.photo)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val unreadMessagesTextView: TextView = itemView.findViewById(R.id.unreadMessagesTextView)
        private val unreadMessagesCardView: CardView = itemView.findViewById(R.id.unreadMessagesCardView)


        fun bind(item: Chat) {

            photo.setImageResource(item.photo)
            nameTextView.text = item.name
            messageTextView.text = item.lastMessage
            timeTextView.text = item.time.toString("HH:mm")

            itemView.setOnClickListener {
                onClickListener?.onChatClick(item)
            }


            if (item.myMessage)
                messageTextView.text = "Вы: " + item.lastMessage
            else
                messageTextView.text = item.lastMessage

            if (item.unreadMessages==0 || item.myMessage){
                unreadMessagesCardView.visibility = View.INVISIBLE
            } else {
                unreadMessagesCardView.visibility = View.VISIBLE
                unreadMessagesTextView.text = item.unreadMessages.toString()
            }

        }
    }
}