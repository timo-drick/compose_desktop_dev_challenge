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

interface Images {
    val title: String
    val resId: String
}

enum class FavoriteImages(override val title: String, override val resId: String) : Images {
    A("Short mantras", "short_mantras"),
    B("Nature meditations", "nature_meditations"),

    C("Stress and anxiety", "stress_and_anxiety"),
    D("Self-massage", "self_massage"),

    E("Overwhelmed", "overwhelmed"),
    F("Nightly wind down", "nightly_wind_down")
}

enum class BodyImages(override val title: String, override val resId: String) : Images {
    A("Inversion", "inversions"),
    B("Quick yoga", "quick_yoda"),
    C("Stretching", "stretching"),
    D("Tabata", "tabata"),
    E("HIIT", "hiit"),
    F("Pre-natal yoga", "pre_natal_yoga")
}

enum class MindImages(override val title: String, override val resId: String) : Images {
    A("Meditate", "meditate"),
    B("With kids", "with_kids"),
    C("Aromatherapy", "aromatherapy"),
    D("On the go", "on_the_go"),
    E("With pets", "with_pets"),
    F("High stress", "high_stress")
}
