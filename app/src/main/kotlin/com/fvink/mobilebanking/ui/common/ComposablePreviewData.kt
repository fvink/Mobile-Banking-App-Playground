package com.fvink.mobilebanking.ui.common

import com.fvink.mobilebanking.domain.Currency
import com.fvink.mobilebanking.domain.Money
import com.fvink.mobilebanking.ui.accounts.AccountBalanceCardViewState
import com.fvink.mobilebanking.ui.accounts.AccountBalanceHistory

object ComposablePreviewData {
    val balance = Money(1_253_243.54, Currency("USD", "$"))
    val accountBalanceHistory = AccountBalanceHistory(
        balanceList = listOf(
            0.0,
            100.0,
            450.0,
            600.0,
            550.0,
            500.0,
            700.0,
            800.0,
            400.0,
            500.0,
            600.0,
            700.0,
            1000.0,
            800.0
        )
    )
    val accountBalanceCardViewState = AccountBalanceCardViewState(
        balance = balance,
        balanceHistory = accountBalanceHistory
    )
}