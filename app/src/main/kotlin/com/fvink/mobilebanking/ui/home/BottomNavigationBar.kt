package com.fvink.mobilebanking.ui.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fvink.mobilebanking.ui.common.theme.ExtendedTheme


@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Accounts,
        NavigationItem.Cards,
        NavigationItem.Empty,
        NavigationItem.Notifications,
        NavigationItem.More,
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = ExtendedTheme.colors.textSubtitle,
        elevation = 0.dp
    ) {
        var selectedItem by remember { mutableStateOf<NavigationItem>(NavigationItem.Accounts) }

        items.forEach { item ->
            if (item is NavigationItem.Empty) {
                // Empty item adds space to fit the floating action button
                BottomNavigationItem(icon = {}, label = { }, selected = false, onClick = { }, enabled = false)
            } else {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = stringResource(id = item.titleResId)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.titleResId),
                            fontSize = 10.sp,
                            softWrap = false
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = ExtendedTheme.colors.textSubtitle,
                    alwaysShowLabel = true,
                    selected = item == selectedItem,
                    onClick = { selectedItem = item }
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}