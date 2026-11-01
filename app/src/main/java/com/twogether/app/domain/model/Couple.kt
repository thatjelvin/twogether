package com.twogether.app.domain.model

data class Couple(
    val coupleId: String,
    val memberIds: List<String>,
    val inviteCode: String?,
    val createdAt: Long,
    val dayTimezone: String?,
)
