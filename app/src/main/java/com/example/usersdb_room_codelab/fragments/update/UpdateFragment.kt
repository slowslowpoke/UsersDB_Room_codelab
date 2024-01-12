package com.example.usersdb_room_codelab.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.usersdb_room_codelab.databinding.FragmentUpdateBinding
import com.example.usersdb_room_codelab.model.User
import com.example.usersdb_room_codelab.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val currentUser = args.currentUser
        with(binding) {
            etUpdateFirstName.setText(currentUser.firstName)
            etUpdateLastName.setText(currentUser.lastName)
            etUpdateAge.setText(currentUser.age.toString())
            btnUpdateUser.setOnClickListener { updateUser() }
        }



        return binding.root
    }

    private fun updateUser() {
        val firstName: String
        val lastName: String
        val age: Int

        // опять же тут тоже неплохо бы добавить проверочку на соответствие...
        binding.apply {
            firstName = etUpdateFirstName.text.toString()
            lastName = etUpdateLastName.text.toString()
            age = Integer.parseInt(etUpdateAge.text.toString())
        }
        val updatedUser = User(args.currentUser.id, firstName, lastName, age)
        mViewModel.updateUser(updatedUser)
        Toast.makeText(requireContext(), "User updated", Toast.LENGTH_SHORT).show()
        val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment()
        findNavController().navigate(action)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}