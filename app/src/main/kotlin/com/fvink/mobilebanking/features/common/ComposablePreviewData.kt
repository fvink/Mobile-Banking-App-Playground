package com.fvink.mobilebanking.features.common

import com.fvink.mobilebanking.domain.Account
import com.fvink.mobilebanking.domain.CounterpartyAccount
import com.fvink.mobilebanking.domain.CounterpartyType
import com.fvink.mobilebanking.domain.Currencies
import com.fvink.mobilebanking.domain.Money
import com.fvink.mobilebanking.domain.Person
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.domain.TransactionType
import com.fvink.mobilebanking.features.accounts.AccountBalanceCardViewState
import com.fvink.mobilebanking.features.accounts.AccountBalanceHistory
import com.fvink.mobilebanking.features.accounts.AccountOverviewViewState
import com.fvink.mobilebanking.features.cards.CardViewState
import com.fvink.mobilebanking.features.cards.CardsViewState
import com.fvink.mobilebanking.features.common.viewstates.TransactionHistoryViewState
import java.time.LocalDateTime

object ComposablePreviewData {
    val usdAccount = Account(
        id = "0",
        iban = "GB53CLRB04066200002723",
        owner = Person("0", "John", "Doe"),
        balance = 1_253_243.53,
        currency = "USD"
    )

    val balance = Money(1_253_243.54, "USD")

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
            accountId = "0",
            balance = balance,
            balanceHistory = accountBalanceHistory
        )
    }

    val accountBalanceCardViewState = AccountBalanceCardViewState(
        accountId = "0",
        balance = balance,
        balanceHistory = accountBalanceHistory
    )

    val transactionHistory = listOf(
        Transaction(
            "0",
            LocalDateTime.of(2022, 3, 6, 12, 12),
            145.88,
            Currencies.usd.code,
            TransactionType.Deposit,
            CounterpartyType.Atm,
            null
        ),
        Transaction(
            "1",
            LocalDateTime.of(2022, 3, 6, 12, 12),
            200.21,
            Currencies.usd.code,
            TransactionType.Deposit,
            CounterpartyType.Bank,
            null
        ),
        Transaction(
            "2",
            LocalDateTime.of(2022, 3, 6, 12, 12),
            303.18,
            Currencies.usd.code,
            TransactionType.Withdrawal,
            CounterpartyType.PersonalAccount,
            CounterpartyAccount("1", "GB53CLRB04066200002723", "Felicia Morris")
        ),
    )

    val accountOverviewViewState = AccountOverviewViewState(
        accountCardViewStates = accountBalanceCardViewStateList,
        transactionHistoryViewState = TransactionHistoryViewState.Data(transactionHistory)
    )

    val cardViewStateList = listOf(
        CardViewState(
            number = "1234 5678 9876 5432",
            expirationDate = "01/23",
            "457"
        ),
        CardViewState(
            number = "1234 5678 9876 5432",
            expirationDate = "12/22",
            "346"
        )
    )

    val cardsViewState = CardsViewState(
        cardViewStates = cardViewStateList,
        transactionHistoryViewState = TransactionHistoryViewState.Data(transactionHistory)
    )
}