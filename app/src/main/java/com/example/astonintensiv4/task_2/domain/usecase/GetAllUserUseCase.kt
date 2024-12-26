package com.example.astonintensiv4.task_2.domain.usecase

import androidx.lifecycle.LiveData
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.domain.repository.UserRepository

class GetAllUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: Int): LiveData<User> {
        return userRepository.getAllUser(userId)
    }
}