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

import kotlin.random.Random

val dogNames = listOf(
    "Bella", "Charlie", "Luna", "Lucy", "Max", "Bailey", "Cooper", "Daisy", "Sadie", "Molly", "Buddy",
    "Lola", "Stella", "Tucker", "Bentley", "Zoey", "Harley", "Maggie", "Riley", "Bear", "Sophie",
    "Duke", "Jax"
).shuffled(Random(1234))

enum class Puppy(
    val dogName: String,
    val photographName: String,
    val link: String,
    val race: DogRace,
    val smallRes: String
) {
    Rebecca(dogNames[0], "Rebecca De Bautista", "https://unsplash.com/@ilcocoloco", DogRace.AIREDALE_TERRIER, "dc1/rebecca_de_bautista_6eofcm4qea4_unsplash.jpg"),
    Tammy(dogNames[1], "Tammy Gann", "https://unsplash.com/@tammynaz", DogRace.AIREDALE_TERRIER, "dc1/tammy_gann__yr96i_pjhk_unsplash.jpg"),
    Berkay(dogNames[2], "Berkay Gumustekin", "https://unsplash.com/@berkaygumustekin", DogRace.GOLDEN_RETRIEVER, "dc1/berkay_gumustekin_ngqyo2ayyne_unsplash.jpg"),
    Patrick(dogNames[3], "Patrick Kool", "https://unsplash.com/@patrick62", DogRace.CAVALIER_KING, "dc1/patrick_kool_06efqvjkib8_unsplash.jpg"),
    Joyce(dogNames[4], "Joyce Zuijdwegt", "https://unsplash.com/@angryyoungpoor", DogRace.CHIHUAHUA, "dc1/joyce_zuijdwegt_gqydwlkye0s_unsplash.jpg")
}
