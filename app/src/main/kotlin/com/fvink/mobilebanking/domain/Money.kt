package com.fvink.mobilebanking.domain

import com.fvink.mobilebanking.ui.common.moneyFormat

data class Money(
    val amount: Double,
    val currency: Currency
)

fun Money.format() = "${amount.moneyFormat()} ${currency.code}"