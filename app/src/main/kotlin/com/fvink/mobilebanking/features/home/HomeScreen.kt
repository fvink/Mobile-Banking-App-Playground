package com.fvink.mobilebanking.features.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvink.mobilebanking.features.cards.CardsScreen
import com.fvink.mobilebanking.features.common.theme.MobileBankingTheme

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                cutoutShape = CircleShape,
                elevation = 22.dp
            ) {
                BottomNavigationBar()
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {

                }
            ) {
                Icon(imageVector = Icons.Filled.Add, tint = Color.White, contentDescription = "new action")
            }
        }
    ) {
        CardsScreen(
            modifier = Modifier.padding(it)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MobileBankingTheme(darkTheme = false) {
        HomeScreen()
    }
}