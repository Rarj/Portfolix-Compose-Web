package com.rioarj.portfolix.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun NavigationHeaderUI(modifier: Modifier = Modifier) {
    val menuInstance = remember { NavigationState() }
    val menus = remember { menuInstance.getNavigationMenus() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        menus.value.forEach { menu ->
            TextButton(
                onClick = { menuInstance.setSelectedMenu(menu.key) },
                content = {
                    Text(
                        text = menu.value.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = menu.value.color(),
                    )
                },
            )
            Spacer(modifier = Modifier.width(24.dp))
        }
    }
}