package com.example.astonintensiv4.task_2.domain.usecase

import android.util.Log
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.domain.repository.UserRepository

class EditUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) {
        Log.d("UserViewModel", "Editing user: $user")
        userRepository.editUser(user)
    }
}