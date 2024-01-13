package com.example.usersdb_room_codelab.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.usersdb_room_codelab.R
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val userToDelete = args.currentUser.firstName
        AlertDialog.Builder(requireContext())
            .setTitle("Delete $userToDelete")
            .setMessage("Are you sure you want to delete $userToDelete?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes"){_, _ ->
                mViewModel.deleteUser(args.currentUser)
                Toast.makeText(requireContext(), "$userToDelete deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToListFragment())}
            .create().show()
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