package com.dyah.bsit.presentation.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah.bsit.domain.usecase.GetTransactionUseCase
import com.dyah.bsit.model.TransactionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel  @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {

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

}
