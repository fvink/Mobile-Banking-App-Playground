package com.fvink.mobilebanking.features.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.features.common.theme.ExtendedTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun TransactionHistoryListPlaceholder(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        repeat(10) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(ExtendedTheme.colors.textSubtitle)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade(),
                        )
                )
                Spacer(modifier = Modifier.width(27.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(25.dp)
                        .clip(CircleShape)
                        .background(ExtendedTheme.colors.textSubtitle)
                        .placeholder(
                            visible = true,
                            shape = RectangleShape,
                            highlight = PlaceholderHighlight.fade(),
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(25.dp)
                        .clip(CircleShape)
                        .background(ExtendedTheme.colors.textSubtitle)
                        .placeholder(
                            visible = true,
                            shape = RectangleShape,
                            highlight = PlaceholderHighlight.fade(),
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionHistoryListPlaceholderPreview() {
    TransactionHistoryListPlaceholder(Modifier.fillMaxWidth())
}