package com.fvink.mobilebanking

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.fvink.mobilebanking.ui.accounts.AccountsOverviewScreen
import com.fvink.mobilebanking.ui.common.theme.MobileBankingTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileBankingTheme(darkTheme = true) {
                AccountsOverviewScreen()
            }
        }
    }
}