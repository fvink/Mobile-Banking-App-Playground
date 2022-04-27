package com.fvink.mobilebanking.features.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.features.common.ComposablePreviewData
import com.fvink.mobilebanking.features.common.moneyFormat
import com.fvink.mobilebanking.features.common.theme.MobileBankingTheme

@Composable
fun AccountBalanceCardView(
    state: AccountBalanceCardViewState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth( )
                .background(MaterialTheme.colors.surface)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp),
                text = state.balance.currency,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSurface
            )

            Text(
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp),
                text = state.balance.amount.moneyFormat(),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            AccountBalanceChartView(
                modifier = Modifier.fillMaxWidth(),
                balanceHistory = state.balanceHistory
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun AccountBalanceCardPreview() {
    MobileBankingTheme(darkTheme = true) {
        AccountBalanceCardView(
            modifier = Modifier.height(240.dp),
            state = AccountBalanceCardViewState(
                accountId = "0",
                balance = ComposablePreviewData.balance,
                balanceHistory = ComposablePreviewData.accountBalanceHistory
            )
        )
    }
}