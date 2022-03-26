package com.fvink.mobilebanking.ui.common

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

private val moneyDecimalFormat = DecimalFormat("###,###.##", DecimalFormatSymbols().apply {
    groupingSeparator = ' '
})

fun Double.moneyFormat(): String = moneyDecimalFormat.format(this)