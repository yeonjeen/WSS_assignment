package com.example.wsselixir.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.data.FollowerInformation
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.remote.UserResponseDto

class FollowersAdapter(private var users: List<UserResponseDto.User>) :
    RecyclerView.Adapter<FollowersAdapter.userViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(user: UserResponseDto.User)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun setUsers(userList: List<UserResponseDto.User>) {
        users = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowerBinding.inflate(inflater, parent, false)
        return userViewHolder(binding)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class userViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val followerImage: ImageView = binding.imgFollower

        fun bind(user: UserResponseDto.User) {
            Glide.with(itemView)
                .load(user.avatar)
                .into(followerImage)

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(user)
            }
        }

    }
}

