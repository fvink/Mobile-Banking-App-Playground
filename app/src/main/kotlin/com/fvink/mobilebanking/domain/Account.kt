package com.fvink.mobilebanking.domain

data class Account(
    val number: Long,
    val owner: Person,
    val balance: Double,
    val currency: Currency
)