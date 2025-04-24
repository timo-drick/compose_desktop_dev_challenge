package com.example.androiddevchallenge.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

inline fun <T> LazyListScope.gridItems(
    columns: Int,
    gridPadding: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(),
    items: List<T>,
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    val chunkedItems = items.chunked(columns)
    itemsIndexed(chunkedItems) { index, rowList ->
        val layoutDirection = LocalLayoutDirection.current
        val topPadding = if (index > 0) gridPadding else contentPadding.calculateTopPadding()
        val startPadding = contentPadding.calculateLeftPadding(layoutDirection)
        val endPadding = contentPadding.calculateEndPadding(layoutDirection)
        val bottomPadding = contentPadding.calculateBottomPadding()
        Row(Modifier.padding(top = topPadding, start = startPadding, bottom = bottomPadding, end = endPadding)) {
            val rowModifier = Modifier.weight(1f)
            rowList.forEachIndexed { index, item ->
                if (index > 0) Spacer(Modifier.width(gridPadding))
                Box(rowModifier) {
                    itemContent(item)
                }
            }
            val emptyRows = (columns - rowList.size)
            repeat(emptyRows) { // fill empty cells
                Spacer(Modifier.width(gridPadding))
                Spacer(modifier = rowModifier)
            }
        }
    }
}


@Composable
fun PaddingValues.add(start: Dp = 0.dp, top: Dp = 0.dp, left: Dp = 0.dp, bottom: Dp = 0.dp) =
    this.plus(PaddingValues(start, top, left, bottom))

@Composable
fun PaddingValues.add(horizontal: Dp = 0.dp, vertical: Dp = 0.dp) =
    this.plus(PaddingValues(horizontal = horizontal, vertical = vertical))

@Composable
fun PaddingValues.add(size: Dp) = this.plus(PaddingValues(size))

@Composable
infix operator fun PaddingValues.plus(padding: PaddingValues): PaddingValues {
    val ld = LocalLayoutDirection.current
    return PaddingValues.Absolute(
        left = calculateLeftPadding(ld) + padding.calculateLeftPadding(ld),
        top = calculateTopPadding() + padding.calculateTopPadding(),
        right = calculateRightPadding(ld) + padding.calculateRightPadding(
            ld
        ),
        bottom = calculateBottomPadding() + padding.calculateBottomPadding()
    )
}

