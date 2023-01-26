package ir.baha.designsystem.widget

import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieListLoadingView() {
    Card(
        modifier = Modifier.height(200.dp),
        backgroundColor = Color.DarkGray
    ) { }
}