package com.fvink.mobilebanking.features.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fvink.mobilebanking.features.common.theme.ExtendedTheme


@Composable
fun BottomNavigationBar(navController: NavController) {
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
        val navBackstackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackstackEntry?.destination?.route

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
                    selected = item.route == currentRoute,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(rememberNavController())
}