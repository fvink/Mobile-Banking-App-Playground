package com.fvink.mobilebanking.features.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fvink.mobilebanking.R
import com.fvink.mobilebanking.core.Screen

sealed class NavigationItem(
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Accounts : NavigationItem(
        route = Screen.Accounts.route,
        titleResId = R.string.navigation_item_accounts,
        iconResId = R.drawable.ic_account
    )

    object Cards : NavigationItem(
        route = Screen.Cards.route,
        titleResId = R.string.navigation_item_cards,
        iconResId = R.drawable.ic_card
    )

    object Notifications : NavigationItem(
        route = Screen.Notifications.route,
        titleResId = R.string.navigation_item_notifications,
        iconResId = R.drawable.ic_notifications
    )

    object More : NavigationItem(
        route = Screen.More.route,
        titleResId = R.string.navigation_item_more,
        iconResId = R.drawable.ic_more
    )

    object Empty : NavigationItem(
        route = "",
        titleResId = 0,
        iconResId = 0
    )
}