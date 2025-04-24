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

import com.example.androiddevchallenge.shared.generated.resources.*
import com.example.androiddevchallenge.shared.generated.resources.Res
import com.example.androiddevchallenge.shared.generated.resources.nature_meditations
import com.example.androiddevchallenge.shared.generated.resources.short_mantras
import com.example.androiddevchallenge.shared.generated.resources.stress_and_anxiety
import de.drick.compose.hotpreview.HotPreview
import org.jetbrains.compose.resources.DrawableResource

@HotPreview(name = "Phone", group = "dark", widthDp = 411, heightDp = 891, fontScale = 1f, darkMode = true,
    density = 1.0f
)
@HotPreview(name = "Phone big font", widthDp = 411, heightDp = 891, fontScale = 1.5f, darkMode = false,
    density = 1.5f
)
@HotPreview(
    name = "Foldable dark",
    group = "dark",
    widthDp = 1000,
    heightDp = 800,
    fontScale = 1f
)
@HotPreview(name = "Foldable", widthDp = 1000, heightDp = 800, fontScale = 1.5f, darkMode = false)
annotation class PreviewCollection


interface Images {
    val title: String
    val resId: DrawableResource
}

enum class FavoriteImages(override val title: String, override val resId: DrawableResource) : Images {
    A("Short mantras", Res.drawable.short_mantras),
    B("Nature meditations", Res.drawable.nature_meditations),

    C("Stress and anxiety", Res.drawable.stress_and_anxiety),
    D("Self-massage", Res.drawable.self_massage),

    E("Overwhelmed", Res.drawable.overwhelmed),
    F("Nightly wind down", Res.drawable.nightly_wind_down)
}

enum class BodyImages(override val title: String, override val resId: DrawableResource) : Images {
    A("Inversion", Res.drawable.inversions),
    B("Quick yoga", Res.drawable.quick_yoda),
    C("Stretching", Res.drawable.stretching),
    D("Tabata", Res.drawable.tabata),
    E("HIIT", Res.drawable.hiit),
    F("Pre-natal yoga", Res.drawable.pre_natal_yoga)
}

enum class MindImages(override val title: String, override val resId: DrawableResource) : Images {
    A("Meditate", Res.drawable.meditate),
    B("With kids", Res.drawable.with_kids),
    C("Aromatherapy", Res.drawable.aromatherapy),
    D("On the go", Res.drawable.on_the_go),
    E("With pets", Res.drawable.with_pets),
    F("High stress", Res.drawable.high_stress)
}
