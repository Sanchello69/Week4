package com.vas.week4.feature_list_chat_screen.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cesarferreira.tempo.toString
import com.vas.week4.R
import com.vas.week4.feature_list_chat_screen.data.model.Chat

class ListChatAdapter : RecyclerView.Adapter<ItemChatViewHolder>() {

    var data = arrayListOf<Chat>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChatViewHolder =
        ItemChatViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemChatViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}

class ItemChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

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

    companion object {
        fun from(parent: ViewGroup): ItemChatViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.chat_item, parent, false)
            return ItemChatViewHolder(view)
        }
    }
}