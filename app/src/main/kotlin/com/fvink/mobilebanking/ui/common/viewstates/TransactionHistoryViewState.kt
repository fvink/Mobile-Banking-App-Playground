package com.fvink.mobilebanking.ui.common.viewstates

import com.fvink.mobilebanking.domain.Transaction

sealed class TransactionHistoryViewState {
    object Loading : TransactionHistoryViewState()
    class Data(val transactionHistory: List<Transaction>) : TransactionHistoryViewState()
    class Error(val message: String) : TransactionHistoryViewState()
}