package com.fvink.mobilebanking.data.repository

import com.fvink.mobilebanking.data.mock.accountsMockResponse
import com.fvink.mobilebanking.domain.Account
import com.fvink.mobilebanking.ui.accounts.AccountBalanceHistory
import com.fvink.mobilebanking.util.DispatcherProvider
import com.fvink.mobilebanking.util.safeExecute
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val dispatchers: DispatcherProvider
) {
    suspend fun getAccounts(): Result<List<Account>> {
        return withContext(dispatchers.io) {
            safeExecute { Json.decodeFromString(accountsMockResponse) }
        }
    }

    suspend fun getAccountBalanceHistory(accountId: String): Result<AccountBalanceHistory> {
        return Result.success(
            AccountBalanceHistory(
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
        )
    }
}