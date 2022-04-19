package com.fvink.mobilebanking.domain

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: String,
    val firstName: String,
    val lastName: String
)

val Person.fullName: String
    get() = "${this.firstName} ${this.lastName}"