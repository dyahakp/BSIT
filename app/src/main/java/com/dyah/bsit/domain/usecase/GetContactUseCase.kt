package com.dyah.bsit.domain.usecase

import com.dyah.bsit.domain.repository.Repository
import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class GetContactUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getContact() : Response<List<ContactResponse>>{
        return repository.getContact()
    }
}