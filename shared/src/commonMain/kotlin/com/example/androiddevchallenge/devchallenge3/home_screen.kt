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
package com.example.androiddevchallenge.devchallenge3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MyTheme
import com.example.androiddevchallenge.shared.gridItems
import com.example.androiddevchallenge.screenPadding
import de.drick.compose.hotpreview.HotPreview
import de.drick.compose.hotpreview.HotPreviewLightDark
import org.jetbrains.compose.resources.painterResource
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

enum class NavItems(val title: String, val vector: ImageVector) {
    HOME("HOME", Icons.Default.Spa),
    PROFILE("PROFILE", Icons.Default.AccountCircle)
}

@HotPreview(name = "Mango", group = "dark", fontScale = 1.50f,
    widthDp = 1280, heightDp = 800, density = 1.5f, darkMode = false
)
@PreviewCollection
@HotPreviewLightDark
@Composable
fun PreviewHomeScreen() {
    MyTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(NavItems.HOME) }
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                containerColor = MaterialTheme.colorScheme.onBackground
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "play",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        bottomBar = {
            BottomAppBar(
                //contentColor = MaterialTheme.colorScheme.background,
                actions = {
                    NavItems.entries.forEach { item ->
                        val selected = selectedTab == item
                        IconButton(
                            //selected = selected,
                            onClick = { selectedTab = item }
                        ) {
                            //label = { Text(item.title, style = MaterialTheme.typography.caption) },
                            Icon(item.vector, item.title, Modifier.size(18.dp))
                        }
                    }
                }
            )
        },
        content = { contentPadding ->
            HomeContent(contentPadding)
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
            Image(painterResource(image.resId), image.title)
            Text(
                text = image.title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleSmall
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
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RoundItem(image: Images) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(image.resId),
            contentDescription = image.title,
            Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = image.title,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = MaterialTheme.typography.titleSmall
        )
    }
}


