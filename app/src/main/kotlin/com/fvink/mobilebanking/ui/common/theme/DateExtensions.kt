package com.fvink.mobilebanking.ui.common.theme

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val simpleDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd")

fun LocalDateTime.simpleDateFormat() = this.format(simpleDateFormatter)

fun LocalDate.simpleDateFormat() = this.format(simpleDateFormatter)