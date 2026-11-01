package com.twogether.app.domain.model

data class User(
    val uid: String,
    val displayName: String?,
    val photoUrl: String?,
    val coupleId: String?,
    val timezone: String?,
)
