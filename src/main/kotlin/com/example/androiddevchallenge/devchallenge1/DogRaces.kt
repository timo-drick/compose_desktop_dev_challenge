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

data class BreedCharacteristic(val name: String, val value: Int)

enum class DogRace(val title: String, val link: String, val description: String, val characteristicList: List<BreedCharacteristic>) {
    AIREDALE_TERRIER(
        title = "Airedale Terrier",
        link = "https://dogtime.com/dog-breeds/airedale-terrier",
        description = """
            Known as the “King of Terriers,” the Airedale is indeed the largest of all terriers. The dog breed originated in the Aire Valley of Yorkshire and was created to catch otters and rats in the region between the Aire and Wharfe Rivers. An able sporting dog, they became an ideal working dog as well, proving their worth during World War I.
            Although these are purebred dogs, you may find them in the care of rescue groups or shelters. Opt to adopt if you want to bring one of these pups home!
            Intelligent, outgoing, and confident, the Airedale Terrier possesses a wonderful playful streak that delights their humans. Novice pet parents and apartment dwellers should beware, though. These dogs have high energy and need plenty of exercise, and their intensity might be a little much for first-time dog trainers. But if you can meet the breed’s physical needs and provide them with space to run, preferably in the form of a big yard with a tall, secure fence, then you’ll be rewarded with a playful, loving companion for the whole family–even kids!
        """.trimIndent(),
        characteristicList = listOf(
            BreedCharacteristic("Apartment Living", 1),
            BreedCharacteristic("Kid-Friendly", 4),
            BreedCharacteristic("Size", 3),
            BreedCharacteristic("Tendency to bark", 4),
            BreedCharacteristic("Easy to train", 4)
        )
    ),
    GOLDEN_RETRIEVER(
        title = "Golden Retriever",
        link = "https://dogtime.com/dog-breeds/golden-retriever",
        description = """
            The Golden Retriever is one of the most popular dog breeds in the United States. The breed’s friendly, tolerant attitude makes them great family pets, and their intelligence makes them highly capable working dogs.
            Golden Retrievers excel at retrieving game for hunters, tracking, sniffing out contraband for law enforcement, and as therapy and service dogs. They’re also natural athletes and do well in dog sports such as agility and competitive obedience.
            These dogs are fairly easy to train and get along in just about any home or family. They’re great with kids and very protective of their humans. If you want a loyal, loving, and smart companion, consider adopting one of these pups into your pack.
        """.trimIndent(),
        characteristicList = listOf(
            BreedCharacteristic("Apartment Living", 2),
            BreedCharacteristic("Kid-Friendly", 5),
            BreedCharacteristic("Friendly toward strangers", 5),
            BreedCharacteristic("Size", 3),
            BreedCharacteristic("Easy to train", 5)
        )
    ),
    CAVALIER_KING(
        title = "Cavalier King Charles Spaniel",
        link = "https://dogtime.com/dog-breeds/cavalier-king-charles-spaniel",
        description = """
            Although they’re born to be a companion, the Cavalier King Charles Spaniel dog breed retains the sporty nature of their spaniel ancestors. If they’re not sitting on a lap or getting a belly rub, nothing makes them happier than to flush a bird and then attempt to retrieve it.
            Although these are purebred dogs, you may still find them in shelters and rescues. Remember to adopt! Don’t shop if you want to bring a dog home.
            One of the largest of the toy breeds, Cavaliers are often as athletic as a true sporting breed and enjoy hiking, running on the beach, and dog sports such as agility, flyball and rally. Some have even shown their prowess as hunting dogs. The more restful members of the breed find success as family friends and therapy dogs.
        """.trimIndent(),
        characteristicList = listOf(
            BreedCharacteristic("Apartment Living", 5),
            BreedCharacteristic("Kid-Friendly", 5),
            BreedCharacteristic("Friendly toward strangers", 5),
            BreedCharacteristic("Size", 2),
            BreedCharacteristic("Easy to train", 4),
            BreedCharacteristic("Tolerates beeing alone", 1)
        )
    ),
    CHIHUAHUA(
        title = "Chihuahua",
        link = "https://dogtime.com/dog-breeds/chihuahua",
        description = """
            The Chihuahua dog breed‘s charms include their small size, big personality, and variety in coat types and colors. They’re all dog, fully capable of competing in dog sports such as agility and obedience, and are among the top ten watchdogs recommended by experts.
            Although these are purebred dogs, you may still find them in shelters and rescues. Remember to adopt! Don’t shop if you want to bring a dog home.
            Chihuahuas love nothing more than being with their people — even novice pet parents — and require a minimum of grooming and exercise. They make excellent apartment dogs who’ll get along with the whole family. Just make sure any children who approach know how to play gently with a small dog.
        """.trimIndent(),
        characteristicList = listOf(
            BreedCharacteristic("Apartment Living", 5),
            BreedCharacteristic("Kid-Friendly", 5),
            BreedCharacteristic("Size", 1),
            BreedCharacteristic("Easy to train", 4),
            BreedCharacteristic("Tolerates beeing alone", 1),
            BreedCharacteristic("Easy to groom", 5)
        )
    )
}
