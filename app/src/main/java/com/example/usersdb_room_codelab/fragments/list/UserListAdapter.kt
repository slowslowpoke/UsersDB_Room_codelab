package com.example.usersdb_room_codelab.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.usersdb_room_codelab.model.User
import com.example.usersdb_room_codelab.databinding.UserListItemBinding

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var mUserList = emptyList<User>()

    class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvId.text = user.id.toString()
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()
                clUserListRow.setOnClickListener{
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user)
                    itemView.findNavController().navigate(action)

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return UserViewHolder(binding)
    }

    override fun getItemCount() = mUserList.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = mUserList[position]
        holder.bind(currentUser)


    }

    fun setData(userList: List<User>){
        this.mUserList = userList
        notifyDataSetChanged()
    }

}