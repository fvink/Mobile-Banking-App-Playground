package com.fvink.mobilebanking.ui.accounts

import com.fvink.mobilebanking.domain.TransactionHistory

data class AccountOverviewViewState(
    val accountCardViewStates: List<AccountBalanceCardViewState>,
    val transactionHistory: TransactionHistory
)