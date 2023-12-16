package com.gym.gymmate.core.data.repository.authentication

import com.gym.gymmate.core.data.model.login.LoginResponse

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): Result<LoginResponse>
}