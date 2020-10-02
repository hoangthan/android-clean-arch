package com.brkr.linagora.data.entity

import com.brkr.linagora.domain.model.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntity(
    val email: String,
    val username: String
) {

    fun toUser(): User {
        return User(
            email = this.email,
            username = this.username
        )
    }
}
