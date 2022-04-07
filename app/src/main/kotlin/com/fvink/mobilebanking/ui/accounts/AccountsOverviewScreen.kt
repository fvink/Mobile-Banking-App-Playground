package com.fvink.mobilebanking.ui.accounts

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.R
import com.fvink.mobilebanking.domain.CounterpartyType
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.domain.TransactionType
import com.fvink.mobilebanking.domain.format
import com.fvink.mobilebanking.domain.title
import com.fvink.mobilebanking.ui.common.ComposablePreviewData
import com.fvink.mobilebanking.ui.common.composables.TransactionIcon
import com.fvink.mobilebanking.ui.common.theme.ExtendedTheme
import com.fvink.mobilebanking.ui.common.theme.MobileBankingTheme
import java.time.LocalDate

@Composable
fun AccountsOverviewScreen() {
    AccountsOverviewView(ComposablePreviewData.accountOverviewViewState)
}

@Composable
fun AccountsOverviewView(state: AccountOverviewViewState) {
    Column(
        modifier = Modifier
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
            selectedAccountIndex = 0, // TODO index
            onPageSelected = {}
        )

        Spacer(modifier = Modifier.height(15.dp))

        TransactionHistory(transactions = state.transactionHistory.transactions)
    }
}

@Composable
fun TransactionHistory(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        var previousDate: LocalDate? = null

        for (transaction in transactions) {
            if (previousDate != transaction.date) {
                previousDate = transaction.date
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 15.dp),
                        text = transaction.date.toString(),
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
                CounterpartyType.Atm, CounterpartyType.Bank -> R.drawable.ic_cash_withdrawal
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
                TransactionType.Deposit -> transaction.amount.format()
                TransactionType.Withdrawal -> "-${transaction.amount.format()}"
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
        AccountsOverviewView(ComposablePreviewData.accountOverviewViewState)
    }
}