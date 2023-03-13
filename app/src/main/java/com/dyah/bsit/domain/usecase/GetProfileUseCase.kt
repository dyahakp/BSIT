package com.dyah.bsit.domain.usecase

import com.dyah.bsit.domain.repository.Repository
import com.dyah.bsit.model.ProfileResponse
import com.dyah.bsit.model.TransactionResponse
import retrofit2.Response
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getProfile() : Response<ProfileResponse>{
        return repository.getProfile()
    }
}