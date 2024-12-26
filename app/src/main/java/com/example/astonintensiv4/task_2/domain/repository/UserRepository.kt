package com.example.astonintensiv4.task_2.domain.repository

import androidx.lifecycle.LiveData
import com.example.astonintensiv4.task_2.domain.model.User

interface UserRepository {
    suspend fun getAllUser(userId: Int): LiveData<User>
    suspend fun getAllUserList(): LiveData<List<User>>
    suspend fun editUser(user: User)
}