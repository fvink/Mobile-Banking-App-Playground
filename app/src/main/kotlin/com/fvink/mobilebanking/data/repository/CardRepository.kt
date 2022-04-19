package com.fvink.mobilebanking.data.repository

import com.fvink.mobilebanking.domain.Card
import com.fvink.mobilebanking.util.DispatcherProvider
import java.time.LocalDate
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val dispatchers: DispatcherProvider
) {
    suspend fun getCards(): Result<List<Card>> {
        val cards = listOf(
            Card(
                id = "0",
                number = "1234 5678 9876 5432",
                expirationDate = LocalDate.of(2023, 1, 12),
                "457"
            ),
            Card(
                id = "1",
                number = "1234 5678 9876 5432",
                expirationDate = LocalDate.of(2023, 12, 10),
                "346"
            )
        )
        return Result.success(cards)
    }
}