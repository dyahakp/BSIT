package com.dyah.bsit.domain.repository

import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response

interface Repository {
    suspend fun getTransaction(): Response<List<TransactionResponse>>
    suspend fun getContact(): Response<List<ContactResponse>>
    suspend fun getProfile(): Response<ProfileResponse>
}