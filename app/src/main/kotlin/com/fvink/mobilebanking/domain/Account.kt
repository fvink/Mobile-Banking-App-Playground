package com.fvink.mobilebanking.domain

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: String,
    val iban: String,
    val owner: Person,
    val balance: Double,
    val currency: String
)