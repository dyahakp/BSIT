package com.dyah.bsit.domain.repository

import com.dyah.bsit.domain.RemoteDataSource
import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getTransaction() : Response<List<TransactionResponse>>{
        return remoteDataSource.getTransaction()
    }

    override suspend fun getContact(): Response<List<ContactResponse>> {
        return remoteDataSource.getContact()
    }

    override suspend fun getProfile(): Response<ProfileResponse> {
        return remoteDataSource.getProfile()
    }

}