package com.fvink.mobilebanking.domain

data class Person(
    val id: Long,
    val firstName: String,
    val lastName: String
)

val Person.fullName: String
    get() = "${this.firstName} ${this.lastName}"