package com.fvink.mobilebanking.ui.common.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val Secondary = Color(0xFF6384FF)
private val ErrorRed = Color(0xFFFF2920)

private val PrimaryLight = Color(0xFFFEFEFE)
private val SurfaceLight = Color(0xFFFFFFFF)
private val TextTitleLight = Color(0xFF293653)
private val TextSubtitleLight = Color(0xFFA5AAC1)

private val PrimaryDark = Color(0xFF1C1E21)
private val SurfaceDark = Color(0xFF232429)
private val TextTitleDark = Color(0xFFFFFFFF)
private val TextSubtitleDark = Color(0xFF797979)

val LightColors = lightColors(
    primary = PrimaryLight,
    onPrimary = TextTitleLight,
    secondary = Secondary,
    surface = SurfaceLight,
    onSurface = TextTitleLight,
    error = ErrorRed
)

val DarkColors = darkColors(
    primary = PrimaryDark,
    onPrimary = TextTitleDark,
    secondary = Secondary,
    surface = SurfaceDark,
    onSurface = TextTitleDark,
    error = ErrorRed
)