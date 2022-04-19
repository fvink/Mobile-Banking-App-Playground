package com.fvink.mobilebanking.data.repository

import com.fvink.mobilebanking.data.mock.transactionsMockResponse
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.util.DispatcherProvider
import com.fvink.mobilebanking.util.safeExecute
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val dispatchers: DispatcherProvider
) {
    suspend fun getTransactions(accountId: String): Result<List<Transaction>> {
        return withContext(dispatchers.io) {
            delay(1200)
            safeExecute { Json.decodeFromString(transactionsMockResponse) }
        }
    }
}