package com.gym.gymmate.core.domain.login

import com.gym.gymmate.core.data.model.login.LoginResponse

data class LoginResult(
    val userId: String = "",
    val token: String = ""
)

fun LoginResponse.toModel(): LoginResult {
    return LoginResult(this.userId, this.token)
}
