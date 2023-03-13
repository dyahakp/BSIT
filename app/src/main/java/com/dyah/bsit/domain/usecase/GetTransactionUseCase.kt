package com.dyah.bsit.domain.usecase

import com.dyah.bsit.domain.repository.Repository
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getTransaction() : Response<List<TransactionResponse>>{
        return repository.getTransaction()
    }
}