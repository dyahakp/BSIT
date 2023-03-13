package com.dyah.bsit.domain

import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response


//mengurus data ke bagaian ke API, REMOTE KE SERVER
interface RemoteDataSource {
    suspend fun getTransaction(): Response<List<TransactionResponse>>
    //fungsi courotine
    suspend fun getContact(): Response<List<ContactResponse>>
    suspend fun getProfile() : Response<ProfileResponse>
}