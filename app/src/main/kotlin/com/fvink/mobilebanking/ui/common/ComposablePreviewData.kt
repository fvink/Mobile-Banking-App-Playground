package com.fvink.mobilebanking.ui.common

import com.fvink.mobilebanking.domain.Account
import com.fvink.mobilebanking.domain.CounterpartyAccount
import com.fvink.mobilebanking.domain.CounterpartyType
import com.fvink.mobilebanking.domain.Currencies
import com.fvink.mobilebanking.domain.Currency
import com.fvink.mobilebanking.domain.Money
import com.fvink.mobilebanking.domain.Person
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.domain.TransactionHistory
import com.fvink.mobilebanking.domain.TransactionType
import com.fvink.mobilebanking.ui.accounts.AccountBalanceCardViewState
import com.fvink.mobilebanking.ui.accounts.AccountBalanceHistory
import com.fvink.mobilebanking.ui.accounts.AccountOverviewViewState
import java.time.LocalDate

object ComposablePreviewData {
    val usdAccount = Account(
        number = 0L,
        owner = Person(0L, "John", "Doe"),
        balance = 1_253_243.53,
        currency = Currency("USD", "$")
    )

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

    val accountBalanceCardViewStateList = (0..4).map {
        AccountBalanceCardViewState(
            balance = balance,
            balanceHistory = accountBalanceHistory
        )
    }

    val accountBalanceCardViewState = AccountBalanceCardViewState(
        balance = balance,
        balanceHistory = accountBalanceHistory
    )

    val transactionHistory = TransactionHistory(
        transactions = listOf(
            Transaction(0L, LocalDate.of(2022, 3, 6), Money(145.88, Currencies.usd), TransactionType.Deposit, CounterpartyType.Atm, null),
            Transaction(1L, LocalDate.of(2022, 3, 6), Money(200.21, Currencies.usd), TransactionType.Deposit, CounterpartyType.Bank, null),
            Transaction(
                2L, LocalDate.of(2022, 3, 6), Money(303.18, Currencies.usd), TransactionType.Withdrawal, CounterpartyType.PersonalAccount,
                CounterpartyAccount(1L, 1L, "Felicia Morris")
            ),
        )
    )

    val accountOverviewViewState = AccountOverviewViewState(
        accountCardViewStates = accountBalanceCardViewStateList,
        transactionHistory = transactionHistory
    )
}