package com.example.astonintensiv4.task_2.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.astonintensiv4.R
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {

    private val users = mutableListOf(
        User(1, "Иван", "Иванов", R.drawable.user_1.toString(), "+7 123 456 78 90"),
        User(2, "Петр", "Петров", R.drawable.user_2.toString(), "+7 234 567 89 01"),
        User(3, "Анна", "Федорова", R.drawable.user_3.toString(), "+7 345 678 90 12"),
        User(4, "Михаил", "Сидоров", R.drawable.user_4.toString(), "+7 456 789 01 23")
    )

    private val userListLiveData = MutableLiveData<List<User>>(users)

    override suspend fun getAllUser(userId: Int): LiveData<User> {
        val user = users.find { it.id == userId }
        return MutableLiveData(user)
    }

    override suspend fun getAllUserList(): LiveData<List<User>> {
        return userListLiveData
    }

    override suspend fun editUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        if (index != -1) {
            users[index] = user
            userListLiveData.postValue(users.toList())
            Log.d("UserRepositoryImpl", "User updated: $user")
        }
    }
}