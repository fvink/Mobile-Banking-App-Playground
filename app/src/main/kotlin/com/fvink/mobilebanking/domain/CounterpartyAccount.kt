package com.fvink.mobilebanking.domain

import kotlinx.serialization.Serializable

@Serializable
data class CounterpartyAccount(
    val id: String,
    val iban: String,
    val name: String
)