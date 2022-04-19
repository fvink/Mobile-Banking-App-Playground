package com.fvink.mobilebanking.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.R
import com.fvink.mobilebanking.ui.common.ComposablePreviewData
import com.fvink.mobilebanking.ui.common.theme.ExtendedTheme
import com.fvink.mobilebanking.ui.common.theme.creditCardSubtitle
import com.fvink.mobilebanking.ui.common.theme.creditCardTitle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardsViewPager(
    cardViewStates: List<CardViewState>,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val pagerState = rememberPagerState()

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                onPageSelected(page)
            }
        }

        HorizontalPager(
            count = cardViewStates.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
        ) { page ->
            CardView(
                modifier = Modifier
                    .offset {
                        val pageOffset = this@HorizontalPager.calculateCurrentOffsetForPage(page)
                        IntOffset(
                            x = (60.dp * pageOffset).roundToPx(),
                            y = 0
                        )
                    },
                state = cardViewStates[page]
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            activeColor = ExtendedTheme.colors.pagerIndicatorSelected,
            inactiveColor = ExtendedTheme.colors.pagerIndicatorUnselected
        )
    }
}

@Composable
private fun CardView(
    state: CardViewState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(279.dp)
            .height(162.dp),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.Transparent,
        elevation = 10.dp
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_card_light),
            contentDescription = "card background"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier,
                text = state.number,
                style = MaterialTheme.typography.creditCardTitle,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(
                        text = "Valid thru",
                        style = MaterialTheme.typography.creditCardSubtitle,
                        color = Color.White.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = state.expirationDate,
                        style = MaterialTheme.typography.creditCardTitle,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(40.dp))

                Column {
                    Text(
                        text = "CVV",
                        style = MaterialTheme.typography.creditCardSubtitle,
                        color = Color.White.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = state.cvv,
                        style = MaterialTheme.typography.creditCardTitle,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier.align(Alignment.Bottom),
                    painter = painterResource(id = R.drawable.ic_visa),
                    contentDescription = "card provider image"
                )
            }
        }
    }
}

@Preview
@Composable
fun AccountBalanceCardViewPagerPreview() {
    CardsViewPager(
        cardViewStates = ComposablePreviewData.cardViewStateList,
        onPageSelected = {}
    )
}