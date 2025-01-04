package com.example.astonintensiv4.task_2.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonintensiv4.task_2.data.UserRepositoryImpl
import com.example.astonintensiv4.task_2.domain.model.User
import com.example.astonintensiv4.task_2.domain.usecase.EditUserUseCase
import com.example.astonintensiv4.task_2.domain.usecase.GetAllUserListUseCase
import com.example.astonintensiv4.task_2.domain.usecase.GetAllUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {

    private val repository = UserRepositoryImpl()
    private val getUserListUseCase = GetAllUserListUseCase(repository)
    private val getUserByIdUseCase = GetAllUserUseCase(repository)
    private val editUserUseCase = EditUserUseCase(repository)

    private val _userList = MutableLiveData<List<User>>()
    private val userList: LiveData<List<User>> get() = _userList

    private val _shouldNotifyUserUpdated = MutableLiveData<Boolean>()
    val shouldNotifyUserUpdated: LiveData<Boolean> get() = _shouldNotifyUserUpdated

    private val _selectedUser = MutableLiveData<User?>()
    val selectedUser: LiveData<User?> get() = _selectedUser

    private val _selectedImage = MutableLiveData<Int?>()
    val selectedImage: LiveData<Int?> get() = _selectedImage

    private val _shouldClose = MutableLiveData<Unit>()
    val shouldClose: LiveData<Unit> get() = _shouldClose

    fun fetchUserList() {
        viewModelScope.launch {
            getUserListUseCase.invoke().observeForever { userList ->
                _userList.postValue(userList)
            }
        }
    }

    fun observeUserList(owner: LifecycleOwner, observer: Observer<List<User>>) {
        userList.observe(owner, observer)
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
            withContext(Dispatchers.IO) {
                editUserUseCase.invoke(user)
            }
            _selectedUser.postValue(user)
            _shouldClose.postValue(Unit)
            _shouldNotifyUserUpdated.postValue(true)
        }
    }

    fun setImageResource(imageResId: Int) {
        _selectedImage.postValue(imageResId)
    }
}

