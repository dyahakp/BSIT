package com.dyah.bsit.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah.bsit.domain.usecase.GetContactUseCase
import com.dyah.bsit.domain.usecase.GetProfileUseCase
import com.dyah.bsit.domain.usecase.GetTransactionUseCase
import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//full logic
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase,
    private val getContactUseCase: GetContactUseCase,
    private val getProfileUseCase: GetProfileUseCase

) : ViewModel() {

    private val _contact = MutableLiveData<List<ContactResponse>>()
    val contact: LiveData<List<ContactResponse>>
        get() = _contact

    private val _profile = MutableLiveData<ProfileResponse>()
    val profile: LiveData<ProfileResponse>
        get() = _profile

    private val _transaction = MutableLiveData<List<TransactionResponse>>()
    val transaction: LiveData<List<TransactionResponse>>
        get() = _transaction


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading


    //dipanggil pertama kali
    //
    fun getTransaction() = viewModelScope.launch {
        _showLoading.postValue(true)
        getTransactionUseCase.getTransaction().let {
            if (it.isSuccessful) {
                _transaction.postValue(it.body())
                _showLoading.postValue(false)
            } else {
                _errorMessage.postValue(it.message())
                _showLoading.postValue(false)

            }
        }
    }

    fun getContact() = viewModelScope.launch {
        _showLoading.postValue(true)
        getContactUseCase.getContact().let {
            if (it.isSuccessful) {
                _contact.postValue(it.body())
                _showLoading.postValue(false)
            } else {
                _errorMessage.postValue(it.message())
                _showLoading.postValue(false)

            }
        }
    }

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
