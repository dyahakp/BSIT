package com.dyah.bsit.presentation.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dyah.bsit.domain.usecase.GetContactUseCase
import com.dyah.bsit.model.ContactResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase
) : ViewModel() {

    private val _contact = MutableLiveData<List<ContactResponse>>()
    val contact: LiveData<List<ContactResponse>>
        get() = _contact

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading
private var dummyContact:MutableList<ContactResponse> = mutableListOf()


    fun getContact() = viewModelScope.launch {
        _showLoading.postValue(true)
        getContactUseCase.getContact().let {
            if (it.isSuccessful) {
                dummyContact.addAll(it.body()?: mutableListOf())
                _contact.postValue(it.body())
                _showLoading.postValue(false)
            } else {
                _errorMessage.postValue(it.message())
                _showLoading.postValue(false)
            }
        }
    }

    fun filterSearchContact(text:String){
       if (text.isEmpty()){
           _contact.postValue(dummyContact)
       }else{
           val filterData = dummyContact.filter {  dataContact ->
               dataContact.name?.contains(text, ignoreCase = true)?: false
           }
           _contact.postValue(filterData)
       }
    }
}