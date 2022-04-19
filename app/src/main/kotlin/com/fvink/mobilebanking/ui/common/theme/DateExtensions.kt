package com.fvink.mobilebanking.ui.common.theme

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val simpleDayMonthFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd")
val simpleMonthYearFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/yy")
val longMonthYearFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM, yyyy")

fun LocalDateTime.simpleDayMonthFormat() = this.format(simpleDayMonthFormatter)

fun LocalDate.simpleDayMonthFormat() = this.format(simpleDayMonthFormatter)

fun LocalDate.simpleMonthYearFormat() = this.format(simpleMonthYearFormatter)

fun LocalDate.longMonthYearFormat() = this.format(longMonthYearFormatter)