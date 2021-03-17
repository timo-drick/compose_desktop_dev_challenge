/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import isSystemInDarkTheme
import setSystemInDarkTheme
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

enum class NavItems(val title: String, val vector: ImageVector) {
    HOME("HOME", Icons.Default.Spa),
    PROFILE("PROFILE", Icons.Default.AccountCircle)
}

@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(NavItems.HOME) }
    val darkTheme = isSystemInDarkTheme()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setSystemInDarkTheme(darkTheme.not()) },
                backgroundColor = MaterialTheme.colors.onBackground
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "play",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.background
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            Surface(
                elevation = bottomNavigationElevation,
                color = MaterialTheme.colors.background
            ) {
                BottomNavigation(
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    NavItems.values().forEach { item ->
                        val selected = selectedTab == item
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { selectedTab = item },
                            label = { Text(item.title, style = MaterialTheme.typography.caption) },
                            icon = { Icon(item.vector, item.title, Modifier.size(18.dp)) }
                        )
                    }
                }
            }
        },
        content = { contentPadding ->
            val fabSizeHalf = 56.dp / 2
            HomeContent(contentPadding.add(bottom = fabSizeHalf + 8.dp))
        }
    )
}

@Composable
fun HomeContent(contentPadding: PaddingValues) {
    BoxWithConstraints {
        LazyColumn(contentPadding = contentPadding) {
            item {
                var searchText by remember { mutableStateOf("") }
                Spacer(Modifier.height(56.dp))
                TextField(
                    value = searchText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(screenPadding),
                    onValueChange = { searchText = it },
                    placeholder = {
                        Text("Search")
                    },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
            item {
                Title("FAVORITE COLLECTIONS")
                Spacer(Modifier.height(8.dp))
            }
            gridItems(
                columns = max(1, (maxWidth / (192.dp + 48.dp)).roundToInt()),
                gridPadding = 8.dp,
                contentPadding = screenPadding,
                items = FavoriteImages.values().toList()
            ) { image ->
                FavoriteItem(image)
            }
            item {
                Spacer(Modifier.height(8.dp))
                Title(title = "ALIGN YOUR BODY")
                Spacer(Modifier.height(8.dp))
            }
            //LazyRow(contentPadding = screenPadding, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val roundIconColumns = max(1, (maxWidth / (128.dp)).roundToInt())
            val bodyItems = BodyImages.values().toList()
            gridItems(
                columns = min(bodyItems.size, roundIconColumns),
                gridPadding = 8.dp,
                contentPadding = screenPadding,
                items = bodyItems
            ) { image ->
                RoundItem(image)
            }
            //}

            item {
                Spacer(Modifier.height(8.dp))
                Title(title = "ALIGN YOUR MIND")
                Spacer(Modifier.height(8.dp))
            }
            val mindItems = MindImages.values().toList()
            gridItems(
                columns = min(mindItems.size, roundIconColumns),
                gridPadding = 8.dp,
                contentPadding = screenPadding,
                items = mindItems
            ) { image ->
                RoundItem(image)
            }
        }
    }
}

@Composable
fun FavoriteItem(image: FavoriteImages) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(imageResource("images/${image.resId}.jpg"), image.title)
            Text(
                text = image.title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        title,
        Modifier
            .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
            .padding(screenPadding)
            .fillMaxWidth(),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RoundItem(image: Images) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            bitmap = imageResource("images/${image.resId}.jpg"),
            contentDescription = image.title,
            Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = image.title,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = MaterialTheme.typography.h3
        )
    }
}

fun PaddingValues.add(start: Dp = 0.dp, top: Dp = 0.dp, end: Dp = 0.dp, bottom: Dp = 0.dp): PaddingValues {
    return object : PaddingValues {
        override fun calculateLeftPadding(layoutDirection: LayoutDirection) =
            this@add.calculateLeftPadding(layoutDirection) +
                if (layoutDirection == LayoutDirection.Ltr) start else end
        override fun calculateTopPadding(): Dp =
            this@add.calculateTopPadding() + top
        override fun calculateRightPadding(layoutDirection: LayoutDirection) =
            this@add.calculateRightPadding(layoutDirection) +
                if (layoutDirection == LayoutDirection.Ltr) end else start
        override fun calculateBottomPadding() =
            this@add.calculateBottomPadding() + bottom
    }
}

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

