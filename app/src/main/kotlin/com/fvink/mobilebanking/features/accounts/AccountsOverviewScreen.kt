package com.fvink.mobilebanking.features.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fvink.mobilebanking.R
import com.fvink.mobilebanking.domain.CounterpartyType
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.domain.TransactionType
import com.fvink.mobilebanking.domain.title
import com.fvink.mobilebanking.features.common.ComposablePreviewData
import com.fvink.mobilebanking.features.common.composables.TransactionIcon
import com.fvink.mobilebanking.features.common.moneyFormat
import com.fvink.mobilebanking.features.common.theme.ExtendedTheme
import com.fvink.mobilebanking.features.common.theme.MobileBankingTheme
import com.fvink.mobilebanking.features.common.theme.simpleDayMonthFormat
import com.fvink.mobilebanking.features.common.viewstates.TransactionHistoryViewState
import java.time.LocalDate

@Composable
fun AccountsOverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountOverviewViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    AccountsOverviewView(
        modifier = modifier,
        state = state,
        onAccountSelected = { index -> viewModel.onAccountSelected(index) }
    )
}

@Composable
fun AccountsOverviewView(
    state: AccountOverviewViewState,
    onAccountSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues(),
            )
    ) {
        AccountBalanceCardViewPager(
            accountCardViewStates = state.accountCardViewStates,
            selectedAccountIndex = state.selectedAccountIndex,
            onPageSelected = onAccountSelected
        )

        Spacer(modifier = Modifier.height(15.dp))

        TransactionHistory(
            modifier = Modifier.fillMaxWidth(),
            state = state.transactionHistoryViewState
        )
    }
}

@Composable
private fun TransactionHistory(
    state: TransactionHistoryViewState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is TransactionHistoryViewState.Loading -> {
            TransactionHistoryListPlaceholder(modifier = modifier)
        }
        is TransactionHistoryViewState.Error -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.message)
            }
        }
        is TransactionHistoryViewState.Data -> {
            TransactionHistoryData(transactions = state.transactionHistory)
        }
    }
}

@Composable
private fun TransactionHistoryData(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        var previousDate: LocalDate? = null

        for (transaction in transactions) {
            if (previousDate != transaction.date.toLocalDate()) {
                previousDate = transaction.date.toLocalDate()
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 15.dp),
                        text = transaction.date.simpleDayMonthFormat(),
                        style = MaterialTheme.typography.subtitle2,
                        color = ExtendedTheme.colors.textSubtitle
                    )
                }
            }

            item { TransactionItem(transaction) }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = when (transaction.counterpartyType) {
                CounterpartyType.Atm, CounterpartyType.Bank, CounterpartyType.Pos -> R.drawable.ic_cash_withdrawal
                CounterpartyType.PersonalAccount -> R.drawable.ic_user
            }
            TransactionIcon(
                iconResId = icon,
                isDepositTransaction = transaction.transactionType == TransactionType.Deposit,
                size = DpSize(46.dp, 46.dp)
            )

            Spacer(modifier = Modifier.width(27.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = transaction.title(),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1
            )

            val amountText = when (transaction.transactionType) {
                TransactionType.Deposit -> "${transaction.amount.moneyFormat()} ${transaction.currency}"
                TransactionType.Withdrawal -> "-${transaction.amount.moneyFormat()} ${transaction.currency}"
            }
            val amountColor = when (transaction.transactionType) {
                TransactionType.Deposit -> MaterialTheme.colors.onSurface
                TransactionType.Withdrawal -> MaterialTheme.colors.error
            }
            Text(
                text = amountText,
                style = MaterialTheme.typography.body1,
                color = amountColor
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(color = ExtendedTheme.colors.divider)
    }
}

@Preview
@Composable
fun AccountsOverviewPreview() {
    MobileBankingTheme(darkTheme = true) {
        AccountsOverviewView(ComposablePreviewData.accountOverviewViewState
            .copy(transactionHistoryViewState = TransactionHistoryViewState.Loading), {})
    }
}