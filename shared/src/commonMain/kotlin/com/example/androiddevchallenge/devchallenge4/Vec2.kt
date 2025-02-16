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
package com.example.androiddevchallenge.devchallenge4

import kotlin.math.sqrt

/** Double-precision 2D vector. */
data class Vec2(val x: Float, val y: Float) {

    constructor(x: Float) : this(x, x)

    /** The Euclidean length of the vector. */
    val length: Float
        get() = sqrt(x * x + y * y)

    /** Returns a normalized version of the vector. (i.e. unit vector) */
    val normalized: Vec2
        get() {
            val localLength = length
            return if (localLength > 0.0) {
                this / localLength
            } else {
                ZERO
            }
        }

    operator fun unaryMinus() = Vec2(-x, -y)

    operator fun plus(vec2: Vec2) = Vec2(x + vec2.x, y + vec2.y)
    operator fun plus(d: Float) = Vec2(x + d, y + d)

    operator fun minus(vec2: Vec2) = Vec2(x - vec2.x, y - vec2.y)
    operator fun minus(d: Float) = Vec2(x - d, y - d)

    operator fun times(d: Float) = Vec2(x * d, y * d)
    operator fun times(v: Vec2) = Vec2(x * v.x, y * v.y)

    operator fun div(d: Float) = Vec2(x / d, y / d)
    operator fun div(d: Vec2) = Vec2(x / d.x, y / d.y)

    companion object {
        val ZERO = Vec2(0.0f, 0.0f)
        val ONE = Vec2(1.0f, 1.0f)
        val UNIT_X = Vec2(1.0f, 0.0f)
        val UNIT_Y = Vec2(0.0f, 1.0f)
    }
}

operator fun Float.times(v: Vec2) = v * this
