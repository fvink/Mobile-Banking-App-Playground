package com.fvink.mobilebanking.features.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.material.Colors as MaterialColors

@Composable
fun MobileBankingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    ExtendedTheme.darkTheme = darkTheme

    val colors = remember(darkTheme) { if (darkTheme) darkColors else lightColors }
    val materialColors = remember(darkTheme) { getMaterialColors(darkTheme, colors) }

    MaterialTheme(
        colors = materialColors,
        typography = MobileBankingTypography
    ) {
        CompositionLocalProvider(
            MobileBankingColors provides colors,
            content = content
        )
    }
}

fun getMaterialColors(darkTheme: Boolean, colors: Colors): MaterialColors {
    return if (darkTheme) {
        darkColors(
            primary = colors.primary,
            secondary = colors.secondary,
            surface = colors.surface
        )
    } else {
        lightColors(
            primary = colors.primary,
            secondary = colors.secondary,
            surface = colors.surface
        )
    }
}

object ExtendedTheme {
    var darkTheme: Boolean = false
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = MobileBankingColors.current
}