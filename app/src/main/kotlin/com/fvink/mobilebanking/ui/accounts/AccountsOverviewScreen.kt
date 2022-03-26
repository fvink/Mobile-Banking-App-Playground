package com.fvink.mobilebanking.ui.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.ui.common.ComposablePreviewData
import com.fvink.mobilebanking.ui.common.theme.MobileBankingTheme

@Composable
fun AccountsOverviewScreen() {
    AccountsOverviewView()
}

@Composable
fun AccountsOverviewView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        AccountBalanceCardView(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            state = ComposablePreviewData.accountBalanceCardViewState
        )
    }
}

@Preview
@Composable
fun AccountsOverviewPreview() {
    MobileBankingTheme(darkTheme = true) {
        AccountsOverviewView()
    }
}