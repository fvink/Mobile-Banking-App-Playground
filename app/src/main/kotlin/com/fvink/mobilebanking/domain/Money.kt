package com.fvink.mobilebanking.domain

import java.math.BigDecimal

data class Money(
    val amount: Double,
    val currency: Currency
)