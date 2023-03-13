package com.dyah.bsit.domain

import com.dyah.bsit.model.ContactResponse
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    //setelah data response dimodel bikin ini
    //https://private-54eacf-fazztrack.apiary-mock.com/questions
    //

    //2 questions dari end point API
    @GET("transaction")
    suspend fun getTransaction() : Response<List<TransactionResponse>>

    @GET("contact")
    suspend fun getContact() : Response<List<ContactResponse>>

    @GET("profile")
    suspend fun getProfile() : Response<ProfileResponse>
}