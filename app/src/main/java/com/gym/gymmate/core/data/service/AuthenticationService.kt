package com.gym.gymmate.core.data.service

import com.gym.gymmate.core.data.model.login.LoginBody
import com.gym.gymmate.core.data.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/login")
    suspend fun login(
        @Body requestBody: LoginBody,
    ): LoginResponse
}
