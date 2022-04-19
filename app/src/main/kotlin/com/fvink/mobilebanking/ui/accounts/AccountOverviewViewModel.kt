package com.fvink.mobilebanking.ui.accounts

import com.fvink.mobilebanking.data.repository.AccountRepository
import com.fvink.mobilebanking.data.repository.TransactionRepository
import com.fvink.mobilebanking.domain.Money
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.ui.base.BaseViewModel
import com.fvink.mobilebanking.ui.base.ViewEvent
import com.fvink.mobilebanking.ui.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountOverviewViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) : BaseViewModel<AccountOverviewViewState, AccountOverviewViewEvent>(AccountOverviewViewState()) {

    init {
        launch {
            showLoading()
            accountRepository.getAccounts().onSuccess { accounts ->
                hideLoading()

                val selectedAccount = accounts.getOrNull(0) ?: return@onSuccess
                val transactionHistory = transactionRepository.getTransactions(selectedAccount.id).getOrNull()

                val accountCardViewStates = accounts.map { account ->
                    val accountBalanceHistory = accountRepository
                        .getAccountBalanceHistory(account.id)
                        .getOrDefault(AccountBalanceHistory(emptyList()))

                    AccountBalanceCardViewState(
                        accountId = account.id,
                        balance = Money(account.balance, account.currency),
                        balanceHistory = accountBalanceHistory
                    )
                }

                updateState {
                    AccountOverviewViewState(
                        accountCardViewStates = accountCardViewStates,
                        selectedAccountIndex = 0,
                        transactionHistoryViewState = TransactionHistoryViewState.Data(transactionHistory.orEmpty())
                    )
                }
            }
        }
    }

    fun onAccountSelected(index: Int) {
        launch {
            updateState {
                it.copy(transactionHistoryViewState = TransactionHistoryViewState.Loading)
            }

            val accountId = getAccountIdByIndex(index) ?: return@launch
            transactionRepository.getTransactions(accountId)
                .onSuccess { transactions ->
                    updateState {
                        it.copy(transactionHistoryViewState = TransactionHistoryViewState.Data(transactions))
                    }
                }.onFailure {
                    updateState {
                        it.copy(transactionHistoryViewState = TransactionHistoryViewState.Error("Failed to load transaction history."))
                    }
                }
        }
    }

    private fun getAccountIdByIndex(index: Int): String? {
        return state.value.accountCardViewStates.getOrNull(index)?.accountId
    }
}

data class AccountBalanceCardViewState(
    val accountId: String,
    val balance: Money,
    val balanceHistory: AccountBalanceHistory
)

sealed class TransactionHistoryViewState {
    object Loading : TransactionHistoryViewState()
    class Data(val transactionHistory: List<Transaction>) : TransactionHistoryViewState()
    class Error(val message: String) : TransactionHistoryViewState()
}

data class AccountOverviewViewState(
    val accountCardViewStates: List<AccountBalanceCardViewState> = emptyList(),
    val selectedAccountIndex: Int = 0,
    val transactionHistoryViewState: TransactionHistoryViewState = TransactionHistoryViewState.Data(emptyList())
) : ViewState

sealed class AccountOverviewViewEvent : ViewEvent {

}