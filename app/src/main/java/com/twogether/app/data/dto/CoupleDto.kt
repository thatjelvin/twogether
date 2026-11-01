package com.twogether.app.data.dto

import com.twogether.app.domain.model.Couple

data class CoupleDto(
    val memberIds: List<String> = emptyList(),
    val inviteCode: String? = null,
    val createdAt: Long = 0,
    val dayTimezone: String? = null,
) {
    fun toModel(coupleId: String): Couple =
        Couple(coupleId, memberIds, inviteCode, createdAt, dayTimezone)

    companion object {
        fun fromModel(couple: Couple): CoupleDto =
            CoupleDto(couple.memberIds, couple.inviteCode, couple.createdAt, couple.dayTimezone)
    }
}
