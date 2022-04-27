package com.fvink.mobilebanking.features.accounts

import com.fvink.mobilebanking.data.repository.AccountRepository
import com.fvink.mobilebanking.data.repository.TransactionRepository
import com.fvink.mobilebanking.domain.Money
import com.fvink.mobilebanking.features.base.BaseViewModel
import com.fvink.mobilebanking.features.base.ViewEvent
import com.fvink.mobilebanking.features.base.ViewState
import com.fvink.mobilebanking.features.common.viewstates.TransactionHistoryViewState
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
                val transactionHistory = transactionRepository.getAccountTransactions(selectedAccount.id).getOrNull()

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
            transactionRepository.getAccountTransactions(accountId)
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

data class AccountOverviewViewState(
    val accountCardViewStates: List<AccountBalanceCardViewState> = emptyList(),
    val selectedAccountIndex: Int = 0,
    val transactionHistoryViewState: TransactionHistoryViewState = TransactionHistoryViewState.Data(emptyList())
) : ViewState

sealed class AccountOverviewViewEvent : ViewEvent {

}