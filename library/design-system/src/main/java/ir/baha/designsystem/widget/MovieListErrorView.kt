package ir.baha.designsystem.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun MovieListErrorView(error: String) {
    Box(
        modifier = Modifier.height(200.dp).testTag("movie_list_error_view"),
        contentAlignment = Alignment.Center
    ) {
        Text(text = error, color = Color.Red)
    }
}