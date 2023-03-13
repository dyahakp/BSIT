package com.dyah.bsit.domain

import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: Service

):RemoteDataSource{
    override suspend fun getTransaction(): Response<List<TransactionResponse>> {
        return service.getTransaction()
    }

    override suspend fun getContact(): Response<List<ContactResponse>> {
        return service.getContact()
    }

    override suspend fun getProfile(): Response<ProfileResponse> {
        return service.getProfile()

    }
}
