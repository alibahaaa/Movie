package ir.baha.designsystem.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieListLoadingView() {
    Box(
        modifier = Modifier
            .testTag("loading_tag")
            .height(200.dp)
            .shimmer()
            .clip(RoundedCornerShape(4.dp)),
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.DarkGray)
                .clip(RoundedCornerShape(4.dp)),
        )
    }
}