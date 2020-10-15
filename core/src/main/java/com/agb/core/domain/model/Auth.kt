package com.agb.core.domain.model

data class Auth(
    val access_token: String,
    val expires_in: Int,
    val token_type: String
) {
    val token: String
        get() = "$token_type $access_token"
}