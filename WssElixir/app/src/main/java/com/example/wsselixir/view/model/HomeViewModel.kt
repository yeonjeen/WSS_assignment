package com.example.wsselixir.view.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.R
import com.example.wsselixir.remote.NetworkModule
import com.example.wsselixir.remote.UserResponseDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _followers = MutableLiveData<List<UserResponseDto.User>>()
    val followers: LiveData<List<UserResponseDto.User>> = _followers

    init {
        fetchData()
    }

    fun registerUserInfo(name: String, mbti: String): Int {
        val isNameBlank = name.isBlank()
        val isMbtiNull = mbti.isEmpty()

        return when {
            isNameBlank && isMbtiNull -> R.string.allFailRegistration
            isNameBlank -> R.string.nameFailRegistration
            isMbtiNull -> R.string.mbtiFailRegistration
            else -> 0
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                val usersResponse = NetworkModule.reqresApi.getUsers()

                val followers = usersResponse.users.map { user ->
                    UserResponseDto.User(
                        avatar = user.avatar,
                        first_name = user.first_name,
                    )
                }
                _followers.postValue(followers)
            } catch (e: Exception) {
                Log.e("Error", "서버연결실패 ${e.message}")
            }
        }
    }

}
