package com.fvink.mobilebanking.ui.common.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.R
import com.fvink.mobilebanking.ui.common.theme.ExtendedTheme

@Composable
fun TransactionIcon(
    @DrawableRes iconResId: Int,
    isDepositTransaction: Boolean,
    size: DpSize
) {
    Box(
        modifier = Modifier.size(width = size.width + 6.dp, height = size.height + 6.dp),
    ) {
        val backgroundColor = ExtendedTheme.colors.transactionIconBackground

        val typeResId = if (ExtendedTheme.darkTheme) {
            if (isDepositTransaction) R.drawable.ic_arrow_left_dark else R.drawable.ic_arrow_right_dark
        } else {
            if (isDepositTransaction) R.drawable.ic_arrow_left_light else R.drawable.ic_arrow_right_light
        }

        Canvas(
            modifier = Modifier
                .size(size)
                .align(Alignment.TopStart)
        ) {
            drawCircle(color = backgroundColor)
        }

        Image(
            modifier = Modifier.align(Alignment.TopStart),
            painter = painterResource(id = iconResId),
            contentDescription = "transaction icon"
        )

        Image(
            modifier = Modifier.align(Alignment.BottomEnd),
            painter = painterResource(id = typeResId),
            contentDescription = "transaction type icon"
        )
    }
}

@Preview
@Composable
fun TransactionIconPreview() {
    TransactionIcon(
        iconResId = R.drawable.ic_cash_withdrawal,
        isDepositTransaction = true,
        size = DpSize(46.dp, 46.dp)
    )
}