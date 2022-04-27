package com.fvink.mobilebanking.features.common.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

object ColorPalette {
    val Secondary = Color(0xFF6384FF)
    val ErrorRed = Color(0xFFFF2920)
    val ButtonRed = Color(0xFFF5586D)

    val PrimaryLight = Color(0xFFFEFEFE)
    val SurfaceLight = Color(0xFFFFFFFF)
    val TextTitleLight = Color(0xFF293653)
    val TextSubtitleLight = Color(0xFFA5AAC1)
    val DividerLight = Color(0xFFF3F3F7)
    val TransactionIconBackgroundLight = Color(0xFFEEEEF1)
    val PagerIndicatorSelectedLight = Color(0xFF595867)
    val PagerIndicatorUnselectedLight = Color(0xFFC6C3CE)

    val PrimaryDark = Color(0xFF1C1E21)
    val SurfaceDark = Color(0xFF232429)
    val TextTitleDark = Color(0xFFFFFFFF)
    val TextSubtitleDark = Color(0xFF797979)
    val DividerDark = Color(0x0DF3F3F7)
    val TransactionIconBackgroundDark = Color(0xFF313338)
    val PagerIndicatorSelectedDark = Color(0xFFAAAAB0)
    val PagerIndicatorUnselectedDark = Color(0x41C6C3CE)
}

data class Colors(
    val primary: Color,
    val secondary: Color,
    val surface: Color,
    val textTitle: Color,
    val textSubtitle: Color,
    val textError: Color,
    val divider: Color,
    val transactionIconBackground: Color,
    val pagerIndicatorSelected: Color,
    val pagerIndicatorUnselected: Color,
    val buttonRed: Color
)

val lightColors = with(ColorPalette) {
    Colors(
        primary = PrimaryLight,
        secondary = Secondary,
        surface = SurfaceLight,
        textTitle = TextTitleLight,
        textSubtitle = TextSubtitleLight,
        textError = ErrorRed,
        divider = DividerLight,
        transactionIconBackground = TransactionIconBackgroundLight,
        pagerIndicatorSelected = PagerIndicatorSelectedLight,
        pagerIndicatorUnselected = PagerIndicatorUnselectedLight,
        buttonRed = ButtonRed
    )
}

val darkColors = with(ColorPalette) {
    Colors(
        primary = PrimaryDark,
        secondary = Secondary,
        surface = SurfaceDark,
        textTitle = TextTitleDark,
        textSubtitle = TextSubtitleDark,
        textError = ErrorRed,
        divider = DividerDark,
        transactionIconBackground = TransactionIconBackgroundDark,
        pagerIndicatorSelected = PagerIndicatorSelectedDark,
        pagerIndicatorUnselected = PagerIndicatorUnselectedDark,
        buttonRed = ButtonRed
    )
}

val MobileBankingColors = compositionLocalOf { lightColors }