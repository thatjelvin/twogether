package com.twogether.app.data.dto

import com.twogether.app.domain.model.User

data class UserDto(
    val displayName: String? = null,
    val photoUrl: String? = null,
    val coupleId: String? = null,
    val timezone: String? = null,
) {
    fun toModel(uid: String): User =
        User(uid, displayName, photoUrl, coupleId, timezone)

    companion object {
        fun fromModel(user: User): UserDto =
            UserDto(user.displayName, user.photoUrl, user.coupleId, user.timezone)
    }
}
