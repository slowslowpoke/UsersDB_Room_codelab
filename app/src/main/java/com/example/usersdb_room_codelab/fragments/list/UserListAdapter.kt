package com.example.usersdb_room_codelab.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usersdb_room_codelab.databinding.UserListItemBinding
import com.example.usersdb_room_codelab.model.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()
                tvPosition.text = user.position

                clUserListRow.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user)
                    root.findNavController().navigate(action)
                }

            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return UserViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = differ.currentList[position]
        holder.bind(currentUser)
    }


}