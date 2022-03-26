package com.fvink.mobilebanking

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.features.accounts.AccountBalanceChartView
import com.fvink.mobilebanking.features.accounts.AccountBalanceHistory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountBalanceChartView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                balanceHistory = AccountBalanceHistory(
                    balanceList = listOf(
                        0.0,
                        100.0,
                        450.0,
                        600.0,
                        550.0,
                        500.0,
                        700.0,
                        800.0,
                        400.0,
                        500.0,
                        600.0,
                        700.0,
                        1000.0,
                        800.0
                    )
                )
            )
        }
    }
}