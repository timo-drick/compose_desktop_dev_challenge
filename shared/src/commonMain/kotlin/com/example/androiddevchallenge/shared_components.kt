package com.example.androiddevchallenge

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
