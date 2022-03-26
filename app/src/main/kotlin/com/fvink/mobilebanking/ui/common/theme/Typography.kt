package com.fvink.mobilebanking.ui.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fvink.mobilebanking.R

private val Gilroy = FontFamily(
    Font(R.font.gilroy_medium)
)

private val KreditBack = FontFamily(
    Font(R.font.kredit_back)
)

private val Rubik = FontFamily(
    Font(R.font.rubik_regular),
    Font(R.font.rubik_medium, FontWeight.Medium)
)

val MobileBankingTypography = Typography(
    defaultFontFamily = Rubik,
    h1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        letterSpacing = (-0.14).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = (-0.06).sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (-0.06).sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        letterSpacing = (-0.03).sp
    ),
)

val Typography.creditCardText: TextStyle
    get() = TextStyle(
        fontFamily = KreditBack,
        fontSize = 8.sp,
        letterSpacing = 2.47.sp
    )