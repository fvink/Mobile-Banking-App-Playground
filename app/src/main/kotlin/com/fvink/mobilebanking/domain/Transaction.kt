package com.fvink.mobilebanking.domain

import java.time.LocalDate

data class Transaction(
    val id: Long,
    val date: LocalDate,
    val amount: Money,
    val transactionType: TransactionType,
    val counterpartyType: CounterpartyType,
    val counterpartyAccount: CounterpartyAccount?,
)

enum class TransactionType {
    Deposit, Withdrawal
}

enum class CounterpartyType {
    Atm, Bank, PersonalAccount
}

// TODO Move this to the right place
fun Transaction.title() = when (counterpartyType) {
    CounterpartyType.Atm -> if (transactionType == TransactionType.Deposit) "ATM cash deposited" else "ATM cash withdrawn"
    CounterpartyType.Bank -> if (transactionType == TransactionType.Deposit) "Bank cash deposited" else "Bank cash withdrawn"
    CounterpartyType.PersonalAccount -> counterpartyAccount!!.name
}