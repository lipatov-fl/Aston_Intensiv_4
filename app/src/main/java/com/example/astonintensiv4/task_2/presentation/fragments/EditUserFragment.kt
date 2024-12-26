package com.example.astonintensiv4.task_2.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.astonintensiv4.R
import com.example.astonintensiv4.databinding.FragmentEditUserBinding
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.presentation.viewmodel.UserViewModel

class EditUserFragment : Fragment() {

    private var imageRes = ""
    private var user: User? = null

    private val viewModel by viewModels<UserViewModel>()

    private var _binding: FragmentEditUserBinding? = null
    private val binding: FragmentEditUserBinding
        get() = _binding ?: throw RuntimeException("FragmentEditUserBinding not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()

        user?.let {
            viewModel.fetchUser(it.id)
        }

        setOnClickListener()
    }

    private fun setObservers() {
        viewModel.selectedUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.editFirstName.setText(it.name)
                binding.editSurname.setText(it.surName)
                binding.editPhoneNumber.setText(it.phone)
                viewModel.setImageResource(it.image.toInt())
            }
        }

        viewModel.selectedImage.observe(viewLifecycleOwner) { imageResId ->
            imageResId?.let {
                binding.userPhoto.setImageResource(it)
                imageRes = it.toString()
            }
        }

        viewModel.shouldClose.observe(viewLifecycleOwner) {
            val resultBundle = Bundle().apply {
                putBoolean(EDIT, true)
            }
            parentFragmentManager.setFragmentResult(
                ListOfUsersFragment.RESULT,
                resultBundle
            )
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setOnClickListener() {
        binding.userPhoto.setOnClickListener {
            openImagePicker()
        }

        binding.buttonSave.setOnClickListener {
            val nameInput = binding.editFirstName.text.toString()
            val lastNameInput = binding.editSurname.text.toString()
            val phoneInput = binding.editPhoneNumber.text.toString()
            Log.d(
                "EditUserFragment",
                "Save button clicked. Input: $nameInput, $lastNameInput, $phoneInput"
            )

            if (nameInput.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле Имя", Toast.LENGTH_SHORT).show()
            } else if (lastNameInput.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле Фамилия", Toast.LENGTH_SHORT)
                    .show()
            } else if (phoneInput.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните поле телефон", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.editUser(
                    User(
                        user?.id ?: 0,
                        nameInput,
                        lastNameInput,
                        imageRes,
                        phoneInput
                    )
                )
            }
        }

    }

    private fun openImagePicker() {
        val images = arrayOf(
            R.drawable.user_1,
            R.drawable.user_2,
            R.drawable.user_3,
            R.drawable.user_4
        )

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose an image")
        builder.setItems(images.map { getDrawableName(it) }.toTypedArray()) { _, which ->
            val selectedImageRes = images[which]
            viewModel.setImageResource(selectedImageRes)
        }
        builder.show()
    }

    private fun getDrawableName(resourceId: Int): String {
        return when (resourceId) {
            R.drawable.user_1 -> "User 1"
            R.drawable.user_2 -> "User 2"
            R.drawable.user_3 -> "User 3"
            R.drawable.user_4 -> "User 4"
            else -> "Unknown"
        }
    }

    companion object {
        private const val USER = "user"
        const val EDIT = "editFinished"
        fun newInstance(user: User) = EditUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }
}