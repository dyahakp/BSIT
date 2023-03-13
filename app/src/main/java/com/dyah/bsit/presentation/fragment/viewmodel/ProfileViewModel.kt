package com.dyah.bsit.presentation.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah.bsit.domain.usecase.GetProfileUseCase
import com.dyah.bsit.model.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _profile = MutableLiveData<ProfileResponse>()
    val profile: LiveData<ProfileResponse>
        get() = _profile

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    fun getProfile() = viewModelScope.launch {
        _showLoading.postValue(true)
        getProfileUseCase.getProfile().let {
            if (it.isSuccessful) {
                _profile.postValue(it.body())
                _showLoading.postValue(false)
            } else {
                _errorMessage.postValue(it.message())
                _showLoading.postValue(false)

            }
        }
    }

}