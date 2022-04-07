package com.fvink.mobilebanking

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.fvink.mobilebanking.ui.common.theme.MobileBankingTheme
import com.fvink.mobilebanking.ui.home.HomeScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
//            val systemUiController = rememberSystemUiController()
//            SideEffect {
//                systemUiController.setStatusBarColor(Color.Transparent)
//            }
            MobileBankingTheme(darkTheme = false) {
                HomeScreen()
            }
        }
    }
}