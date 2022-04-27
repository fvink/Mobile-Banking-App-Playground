package com.fvink.mobilebanking.features.cards

data class CardViewState(
    val number: String,
    val expirationDate: String,
    val cvv: String
)
