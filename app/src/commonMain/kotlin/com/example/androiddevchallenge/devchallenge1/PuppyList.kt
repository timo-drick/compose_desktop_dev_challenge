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
package com.example.androiddevchallenge.devchallenge1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.androiddevchallenge.gridItems
import org.jetbrains.compose.resources.painterResource

@Composable
fun Overview(onSelect: (Puppy, Rect) -> Unit) {
    val itemSpacing = 8.dp
    LazyColumn(
        contentPadding = PaddingValues(itemSpacing)
    ) {
        gridItems(columns = 2, items = Puppy.values().toList(), gridPadding = itemSpacing) { item ->
            Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
                PuppyListItem(puppy = item, onClick = onSelect)
            }
        }
    }
}

@Composable
fun PuppyListItem(puppy: Puppy, onClick: (Puppy, Rect) -> Unit) {
    var imagePosition: Rect? = null
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                imagePosition?.let {
                    onClick(puppy, it)
                }
            }
            .aspectRatio(1.33f)
    ) {
        Image(
            painter = painterResource(puppy.smallRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    val rootOffset = it.localToRoot(Offset.Zero)
                    imagePosition = Rect(rootOffset, it.size.toSize())
                }
        )
        Box(modifier = Modifier.align(Alignment.BottomCenter), contentAlignment = Alignment.TopStart) {
            ImageCaption(title = puppy.dogName, creator = puppy.photographName, race = puppy.race.title)
        }
    }
}

@Composable
fun ImageCaption(title: String, race: String, creator: String?) {
    val color = MaterialTheme.colors.primaryVariant.copy(alpha = .5f)
    Surface(elevation = 0.dp, color = color, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(6.dp)) {
            Text(text = title, style = MaterialTheme.typography.h5, maxLines = 2)
            Text(text = race, maxLines = 2)
            creator?.let { Text("photo by $it", maxLines = 2) }
        }
    }
}
