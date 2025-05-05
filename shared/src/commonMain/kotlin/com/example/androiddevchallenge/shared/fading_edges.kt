package com.example.androiddevchallenge.shared

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MyTheme
import com.example.androiddevchallenge.devchallenge3.HomeContent
import de.drick.compose.hotpreview.HotPreview

@HotPreview(widthDp = 411, heightDp = 891, density = 2.625f)
@Composable
private fun PreviewFadingEdge() {
    val contentPadding = PaddingValues(16.dp)
    MyTheme {
        Scaffold(Modifier.fadingEdge(contentPadding)) {
            HomeContent(contentPadding)
        }
    }

}

fun Modifier.fadingEdge(paddingValues: PaddingValues) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        val top = paddingValues.calculateTopPadding().toPx()
        if (top > 0) {
            val fade = Brush.verticalGradient(
                0.0f to Color.Transparent,
                0.5f to Color.Black.copy(alpha = 0.7f),
                1.0f to Color.Black,
                endY = top
            )
            drawRect(
                brush = fade,
                size = Size(size.width, top),
                blendMode = BlendMode.DstIn
            )
        }
        val bottom = paddingValues.calculateBottomPadding().toPx()
        if (bottom > 0) {
            val offset = size.height - bottom
            val fade = Brush.verticalGradient(
                0.0f to Color.Black,
                0.5f to Color.Black.copy(alpha = 0.3f),
                1.0f to Color.Transparent,
                startY = offset,
                endY = size.height
            )
            drawRect(
                brush = fade,
                topLeft = Offset(0f, offset),
                size = Size(size.width, bottom),
                blendMode = BlendMode.DstIn
            )
        }
    }

private data class StickyType(val contentType: Any?)

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.stickyHeaderContentPaddingAware(
    listState: LazyListState,
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable LazyItemScope.() -> Unit
) {
    stickyHeader(
        key = key,
        contentType = StickyType(contentType),
        content = {
            Layout(content = { content() }) { measurables, constraints ->
                val placeable = measurables.first().measure(constraints)
                val width = constraints.constrainWidth(placeable.width)
                val height = constraints.constrainHeight(placeable.height)
                layout(width, height) {
                    val posY = coordinates?.positionInParent()?.y?.toInt() ?: 0
                    val paddingTop = listState.layoutInfo.beforeContentPadding
                    var top = (paddingTop - posY).coerceIn(0, paddingTop)
                    if (top > 0) {
                        val second = listState.layoutInfo.visibleItemsInfo
                            .filter { it.contentType is StickyType }
                            .getOrNull(1)
                        if (second != null && second.key != key) {
                            val secondOffset = second.offset
                            if (secondOffset <= height) {
                                top -= (height - secondOffset)
                            }
                        }
                    }
                    placeable.place(0, top)
                }
            }
        }
    )
}
