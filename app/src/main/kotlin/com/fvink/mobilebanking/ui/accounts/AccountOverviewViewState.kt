package com.fvink.mobilebanking.ui.accounts

import com.fvink.mobilebanking.domain.Account
import com.fvink.mobilebanking.domain.TransactionHistory

data class AccountOverviewViewState(
    val account: Account,
    val transactionHistory: TransactionHistory
)