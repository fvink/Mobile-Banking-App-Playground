package com.fvink.mobilebanking.domain

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val code: String,
    val symbol: String
)