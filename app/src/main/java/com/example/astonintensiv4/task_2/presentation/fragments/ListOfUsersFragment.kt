package com.example.astonintensiv4.task_2.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.FragmentListOfUsersBinding
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.presentation.adapter.UserAdapter
import com.example.astonintensiv4.task_2.presentation.viewmodel.UserViewModel
class ListOfUsersFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }
    private val userAdapter by lazy {
        UserAdapter()
    }

    private lateinit var binding: FragmentListOfUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userListRecycler.adapter = userAdapter

        userAdapter.setOnClickUserListener { user, _ ->
            navigateToEditUserFragment(user)
        }

        if (savedInstanceState == null) {
            viewModel.fetchUserList()
        }

        viewModel.observeUserList(viewLifecycleOwner) { userList ->
            userAdapter.submitList(userList)
        }

        viewModel.shouldNotifyUserUpdated.observe(viewLifecycleOwner) { isUpdated ->
            if (isUpdated) {
                viewModel.fetchUserList()
                Log.d("ListOfUsersFragment", "User list updated due to edit")
            }
        }

        parentFragmentManager.setFragmentResultListener(
            RESULT,
            this@ListOfUsersFragment.viewLifecycleOwner
        ) { _, bundle ->
            val isUpdatedUser = bundle.getBoolean(EditUserFragment.EDIT)
            Log.d("ListOfUsersFragment", "Received result: isUpdatedUser = $isUpdatedUser")
            if (isUpdatedUser) {
                viewModel.fetchUserList()
                Log.d("ListOfUsersFragment", "Updating user list")
            }
        }
    }

    private fun navigateToEditUserFragment(user: User) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, EditUserFragment.newInstance(user))
            .addToBackStack(FRAGMENT_NAME)
            .commit()
    }

    companion object {
        const val FRAGMENT_NAME = "user_list"
        const val RESULT = "editUser"
    }
}