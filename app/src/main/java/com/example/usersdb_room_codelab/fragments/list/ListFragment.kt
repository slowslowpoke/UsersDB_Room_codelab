package com.example.usersdb_room_codelab.fragments.list

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersdb_room_codelab.R
import com.example.usersdb_room_codelab.viewmodel.UserViewModel
import com.example.usersdb_room_codelab.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //clickListener for FAB
        val action = ListFragmentDirections.actionListFragmentToAddFragment()
        binding.fabAddUser.setOnClickListener {
            findNavController().navigate(action)
        }

        //RecyclerView
        recyclerView = binding.recyclerView
        val adapter =  UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //ViewModel
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.allData.observe(
            viewLifecycleOwner
        ) { userList -> adapter.setData(userList) }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete all users")
            .setMessage("Are you sure you want to delete all users?")
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes"){_, _ ->
                mUserViewModel.deleteAllUsers()
                Toast.makeText(requireContext(), "All users deleted", Toast.LENGTH_SHORT).show()}
            .create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}