package com.example.usersdb_room_codelab.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}