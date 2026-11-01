package com.twogether.app.domain.model

data class StepCompletion(
    val dayKey: String,
    val reflectionDone: Boolean = false,
    val prayerPosted: Boolean = false,
    val puzzleDone: Boolean = false,
    val completedAt: Long? = null,
)
