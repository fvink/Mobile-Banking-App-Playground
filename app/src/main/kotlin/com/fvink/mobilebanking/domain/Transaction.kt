package com.fvink.mobilebanking.domain

import com.fvink.mobilebanking.data.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Transaction(
    val id: String,
    @Serializable(LocalDateTimeSerializer::class) val date: LocalDateTime,
    val amount: Double,
    val currency: String,
    val transactionType: TransactionType,
    val counterpartyType: CounterpartyType,
    val counterpartyAccount: CounterpartyAccount? = null,
)

@Serializable
enum class TransactionType {
    Deposit, Withdrawal
}

@Serializable
enum class CounterpartyType {
    Atm, Bank, Pos, PersonalAccount
}

// TODO Move this to the right place
fun Transaction.title() = when (counterpartyType) {
    CounterpartyType.Atm -> if (transactionType == TransactionType.Deposit) "ATM cash deposited" else "ATM cash withdrawn"
    CounterpartyType.Bank -> if (transactionType == TransactionType.Deposit) "Bank cash deposited" else "Bank cash withdrawn"
    CounterpartyType.Pos -> "POS payment"
    CounterpartyType.PersonalAccount -> counterpartyAccount!!.name
}