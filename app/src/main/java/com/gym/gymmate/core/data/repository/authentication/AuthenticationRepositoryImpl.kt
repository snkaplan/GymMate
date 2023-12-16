package com.gym.gymmate.core.data.repository.authentication

import com.gym.gymmate.core.data.model.login.LoginResponse
import com.gym.gymmate.core.data.remotedatasource.authentication.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource,
) : AuthenticationRepository {
    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return authenticationRemoteDataSource.login(username, password)
    }
}