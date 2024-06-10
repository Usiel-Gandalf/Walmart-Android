package com.usi.exam.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FloatingButton(
    icon: ImageVector? = null,
    textButtonDescription: String? = null,
    actionFloatButton: () -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(16.dp),
        icon = {
            icon?.let {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        text = {
            textButtonDescription?.let {
                Text(text = textButtonDescription)
            }

        },
        onClick = { actionFloatButton.invoke() }
    )
}
