package com.example.astonintensiv4.task_2.domain.usecase

import androidx.lifecycle.LiveData
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.domain.repository.UserRepository

class GetAllUserListUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): LiveData<List<User>> {
        return userRepository.getAllUserList()
    }
}