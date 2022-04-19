package com.fvink.mobilebanking.ui.cards

import com.fvink.mobilebanking.data.repository.CardRepository
import com.fvink.mobilebanking.data.repository.TransactionRepository
import com.fvink.mobilebanking.ui.base.BaseViewModel
import com.fvink.mobilebanking.ui.base.ViewEvent
import com.fvink.mobilebanking.ui.base.ViewState
import com.fvink.mobilebanking.ui.common.theme.simpleMonthYearFormat
import com.fvink.mobilebanking.ui.common.viewstates.TransactionHistoryViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) : BaseViewModel<CardsViewState, CardsViewEvent>(CardsViewState()) {

    init {
        launch {
            showLoading()

            cardRepository.getCards().onSuccess { cards ->
                val selectedCard = cards.getOrNull(0) ?: return@onSuccess
                val transactionHistory = transactionRepository.getCardTransactions(selectedCard.number).getOrNull()

                val cardViewStates = cards.map { card ->
                    CardViewState(
                        number = card.number,
                        expirationDate = card.expirationDate.simpleMonthYearFormat(),
                        cvv = card.cvv
                    )
                }

                updateState {
                    CardsViewState(
                        cardViewStates = cardViewStates,
                        transactionHistoryViewState = TransactionHistoryViewState.Data(transactionHistory.orEmpty())
                    )
                }
            }
        }
    }

    fun onCardSelected(index: Int) {

    }

    fun onLockCardClicked() {

    }

    fun onCardPinClicked() {

    }

    fun onLimitsClicked() {

    }

    fun onSecurityClicked() {

    }
}

data class CardsViewState(
    val cardViewStates: List<CardViewState> = emptyList(),
    val transactionHistoryViewState: TransactionHistoryViewState = TransactionHistoryViewState.Data(emptyList())
) : ViewState

sealed class CardsViewEvent : ViewEvent {

}