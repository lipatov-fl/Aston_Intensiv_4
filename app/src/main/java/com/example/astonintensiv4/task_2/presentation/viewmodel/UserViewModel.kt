package com.example.astonintensiv4.task_2.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonintensiv4.task_2.data.UserRepositoryImpl
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.domain.usecase.EditUserUseCase
import com.example.astonintensiv4.task_2.domain.usecase.GetAllUserListUseCase
import com.example.astonintensiv4.task_2.domain.usecase.GetAllUserUseCase
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepositoryImpl()
    private val getUserListUseCase = GetAllUserListUseCase(repository)
    private val getUserByIdUseCase = GetAllUserUseCase(repository)
    private val editUserUseCase = EditUserUseCase(repository)

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _selectedUser = MutableLiveData<User?>()
    val selectedUser: LiveData<User?> get() = _selectedUser

    private val _selectedImage = MutableLiveData<Int?>()
    val selectedImage: LiveData<Int?> get() = _selectedImage

    private val _shouldClose = MutableLiveData<Unit>()
    val shouldClose: LiveData<Unit> get() = _shouldClose

    fun getUserList() {
        viewModelScope.launch {
            val userListLiveData = getUserListUseCase.invoke()
            userListLiveData.observeForever { userList ->
                _userList.postValue(userList)
            }
        }
    }

    fun fetchUser(userId: Int) {
        viewModelScope.launch {
            val user = getUserByIdUseCase.invoke(userId).value
            _selectedUser.postValue(user)
            user?.let {
                _selectedImage.postValue(it.image.toInt())
            }
        }
    }

    fun editUser(user: User) {
        viewModelScope.launch {
            Log.d("UserViewModel", "Editing user: $user")
            editUserUseCase.invoke(user)
            _selectedUser.postValue(user)
            _shouldClose.postValue(Unit)
        }
    }

    fun setImageResource(imageResId: Int) {
        _selectedImage.postValue(imageResId)
    }
}

