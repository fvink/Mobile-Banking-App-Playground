package com.fvink.mobilebanking.core

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.fvink.mobilebanking.features.accounts.AccountsOverviewScreen
import com.fvink.mobilebanking.features.cards.CardsScreen
import com.fvink.mobilebanking.features.common.FadeThroughEnterTransition
import com.fvink.mobilebanking.features.common.FadeThroughExitTransition
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

internal sealed class Screen(val route: String) {
    object Accounts : Screen("accounts")
    object Cards : Screen("cards")
    object Notifications : Screen("notifications")
    object More : Screen("more")
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Accounts.route,
        enterTransition = { FadeThroughEnterTransition },
        popEnterTransition = { FadeThroughEnterTransition },
        exitTransition = { FadeThroughExitTransition },
        popExitTransition = { FadeThroughExitTransition },
        modifier = modifier
    ) {
        composable(route = Screen.Accounts.route) {
            AccountsOverviewScreen()
        }
        composable(route = Screen.Cards.route) {
            CardsScreen()
        }
        composable(route = Screen.Notifications.route) {

        }
        composable(route = Screen.More.route) {

        }
    }
}