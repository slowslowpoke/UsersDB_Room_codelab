package com.example.usersdb_room_codelab.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.usersdb_room_codelab.model.User
import com.example.usersdb_room_codelab.viewmodel.UserViewModel
import com.example.usersdb_room_codelab.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        _binding = FragmentAddBinding.inflate(inflater, container, false)


        ///!!!вот тут как бы гугл советует такие вещи делать уже в onViewCreated... проверить потом. но пока работает
        binding.btnAddUser.setOnClickListener { insertUserToDatabase() }
        return binding.root
    }

    private fun insertUserToDatabase() {
        val firstName = binding.etAddFirstName.text.toString()
        val lastName = binding.etAddLastName.text.toString()
        val age = Integer.parseInt(binding.etAddAge.text.toString())
        val position = binding.etAddPosition.text.toString()
// Надо бы добавить проверку на правильность введенных данных...

        val newUser = User(0, firstName, lastName, age, position)
        Log.d("ADD FRAGMENT", newUser.toString())
        mViewModel.addUser(newUser)
        Toast.makeText(requireContext(), "Successfully added a user", Toast.LENGTH_SHORT).show()
        val action = AddFragmentDirections.actionAddFragmentToListFragment()
        findNavController().navigate(action)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}