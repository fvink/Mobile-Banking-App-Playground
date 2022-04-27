package com.fvink.mobilebanking.features.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.features.common.ComposablePreviewData
import com.fvink.mobilebanking.features.common.theme.ExtendedTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AccountBalanceCardViewPager(
    accountCardViewStates: List<AccountBalanceCardViewState>,
    selectedAccountIndex: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val pagerState = rememberPagerState(initialPage = selectedAccountIndex)

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                Timber.d("page: $page")
                onPageSelected(page)
            }
        }

        HorizontalPager(
            count = accountCardViewStates.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
        ) { page ->
            AccountBalanceCardView(
                modifier = Modifier
                    .offset {
                        val pageOffset = this@HorizontalPager.calculateCurrentOffsetForPage(page)
                        IntOffset(
                            x = ((-10).dp * pageOffset).roundToPx(),
                            y = 0
                        )
                    },
                state = accountCardViewStates[page]
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

@Preview
@Composable
fun AccountBalanceCardViewPagerPreview() {
    AccountBalanceCardViewPager(
        accountCardViewStates = ComposablePreviewData.accountBalanceCardViewStateList,
        selectedAccountIndex = 0,
        onPageSelected = {}
    )
}