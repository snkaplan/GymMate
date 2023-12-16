package com.gym.gymmate.core.data.remotedatasource.authentication

import com.gym.gymmate.core.data.model.login.LoginBody
import com.gym.gymmate.core.data.model.login.LoginResponse
import com.gym.gymmate.core.data.service.AuthenticationService
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
) : AuthenticationRemoteDataSource {
    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return runCatching {
            authenticationService.login(LoginBody(username, password))
        }
    }
}