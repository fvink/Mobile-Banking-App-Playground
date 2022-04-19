package com.fvink.mobilebanking.domain

import java.time.LocalDate

data class Card(
    val id: String,
    val number: String,
    val expirationDate: LocalDate,
    val cvv: String
)
