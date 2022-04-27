package com.fvink.mobilebanking.domain

import com.fvink.mobilebanking.features.common.moneyFormat

data class Money(
    val amount: Double,
    val currency: String
)

fun Money.format() = "${amount.moneyFormat()} $currency"