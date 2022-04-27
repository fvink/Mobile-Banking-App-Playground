package com.fvink.mobilebanking.features.cards

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fvink.mobilebanking.R
import com.fvink.mobilebanking.domain.Transaction
import com.fvink.mobilebanking.features.accounts.TransactionHistoryListPlaceholder
import com.fvink.mobilebanking.features.accounts.TransactionItem
import com.fvink.mobilebanking.features.common.ComposablePreviewData
import com.fvink.mobilebanking.features.common.theme.ExtendedTheme
import com.fvink.mobilebanking.features.common.theme.MobileBankingTheme
import com.fvink.mobilebanking.features.common.theme.longMonthYearFormat
import com.fvink.mobilebanking.features.common.viewstates.TransactionHistoryViewState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import java.time.LocalDate

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    viewModel: CardsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    CardsView(
        modifier = modifier,
        state = state,
        onCardSelected = viewModel::onCardSelected,
        onLockCardClicked = viewModel::onLockCardClicked,
        onCardPinClicked = viewModel::onCardPinClicked,
        onLimitsClicked = viewModel::onLimitsClicked,
        onSecurityClicked = viewModel::onSecurityClicked
    )
}

@Composable
private fun CardsView(
    state: CardsViewState,
    onCardSelected: (Int) -> Unit,
    onLockCardClicked: () -> Unit,
    onCardPinClicked: () -> Unit,
    onLimitsClicked: () -> Unit,
    onSecurityClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Vertical)
                    .asPaddingValues(),
            )
    ) {
        CardsViewPager(
            cardViewStates = state.cardViewStates,
            onPageSelected = onCardSelected
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 37.dp)
        ) {
            CardOptionButton(
                icon = R.drawable.ic_lock,
                text = R.string.lock_card,
                onClick = onLockCardClicked
            )
            Spacer(modifier = Modifier.weight(1f))
            CardOptionButton(
                icon = R.drawable.ic_pin,
                text = R.string.card_pin,
                onClick = onCardPinClicked
            )
            Spacer(modifier = Modifier.weight(1f))
            CardOptionButton(
                icon = R.drawable.ic_options,
                text = R.string.limits,
                onClick = onLimitsClicked
            )
            Spacer(modifier = Modifier.weight(1f))
            CardOptionButton(
                icon = R.drawable.ic_security,
                text = R.string.security,
                onClick = onSecurityClicked
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TransactionHistory(
            modifier = Modifier.fillMaxWidth(),
            state = state.transactionHistoryViewState
        )
    }
}

@Composable
private fun CardOptionButton(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, radius = 23.dp),
                    onClick = onClick
                )
                .size(46.dp)
                .background(
                    color = ExtendedTheme.colors.secondary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "icon"
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.caption.copy(fontSize = 12.sp),
            color = MaterialTheme.colors.onSurface,
        )
    }
}

@Composable
private fun TransactionHistory(
    state: TransactionHistoryViewState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is TransactionHistoryViewState.Loading -> {
            TransactionHistoryListPlaceholder(modifier = modifier)
        }
        is TransactionHistoryViewState.Error -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.message)
            }
        }
        is TransactionHistoryViewState.Data -> {
            TransactionHistoryData(transactions = state.transactionHistory)
        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
private fun HorizontalDatePicker(
    today: LocalDate,
    modifier: Modifier = Modifier
) {
    val dates = (0..50).map {
        today.minusMonths(it.toLong())
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val endContentPadding = screenWidth / 2 - 40.dp

    var selectedDate by remember { mutableStateOf(today) }
    val scrollState = rememberLazyListState()

    LaunchedEffect(selectedDate) {
        scrollState.animateScrollToItem(dates.indexOf(selectedDate))
    }

    Column(
        modifier = modifier
    ) {
        LazyRow(
            modifier = Modifier,
            state = scrollState,
            reverseLayout = true,
            contentPadding = PaddingValues(end = endContentPadding)
        ) {
            itemsIndexed(items = dates) { index: Int, date: LocalDate ->
                Column(
                    modifier = Modifier
                        .clickable { selectedDate = date }
                ) {
                    var width by remember { mutableStateOf(0) }
                    val density = LocalDensity.current
                    val widthDp = remember(width, density) { with(density) { width.toDp() } }

                    Text(
                        modifier = Modifier
                            .onSizeChanged { width = it.width }
                            .padding(10.dp),
                        text = date.longMonthYearFormat(),
                        style = MaterialTheme.typography.body2,
                        fontWeight = if (date == selectedDate) FontWeight.Medium else FontWeight.Normal,
                        color = if (date == selectedDate) ExtendedTheme.colors.secondary else ExtendedTheme.colors.textSubtitle
                    )
                    Divider(
                        modifier = Modifier
                            .width(widthDp)
                            .align(Alignment.CenterHorizontally),
                        color = if (date == selectedDate) ExtendedTheme.colors.secondary else Color.Transparent,
                        thickness = 2.dp
                    )
                }
            }
        }

        Divider(color = ExtendedTheme.colors.divider)
    }
}

@Composable
private fun TransactionHistoryData(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HorizontalDatePicker(today = LocalDate.now())
        LazyColumn {
            for (transaction in transactions) {
                item { TransactionItem(transaction) }
            }
        }
    }
}

@Preview
@Composable
private fun CardsViewPreview() {
    MobileBankingTheme(darkTheme = true) {
        CardsView(
            state = ComposablePreviewData.cardsViewState,
            onCardSelected = {},
            onLockCardClicked = {},
            onCardPinClicked = {},
            onLimitsClicked = {},
            onSecurityClicked = {}
        )
    }
}