package com.fvink.mobilebanking.features.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.fvink.mobilebanking.R

sealed class NavigationItem(
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Accounts : NavigationItem(
        titleResId = R.string.navigation_item_accounts,
        iconResId = R.drawable.ic_account
    )

    object Cards : NavigationItem(
        titleResId = R.string.navigation_item_cards,
        iconResId = R.drawable.ic_card
    )

    object Notifications : NavigationItem(
        titleResId = R.string.navigation_item_notifications,
        iconResId = R.drawable.ic_notifications
    )

    object More : NavigationItem(
        titleResId = R.string.navigation_item_more,
        iconResId = R.drawable.ic_more
    )

    object Empty : NavigationItem(
        titleResId = 0,
        iconResId = 0
    )
}