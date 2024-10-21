package com.jnasser.pokeapp.core.presentation.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomCircularProgressIndicator(modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onSurface) {
    CircularProgressIndicator(
        modifier = modifier
            .size(50.dp),
        strokeWidth = 2.dp,
        color = color
    )
}