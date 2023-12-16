package com.gym.gymmate.core.data.remotedatasource.authentication

import com.gym.gymmate.core.data.model.login.LoginResponse

interface AuthenticationRemoteDataSource {
    suspend fun login(username: String, password: String): Result<LoginResponse>
}