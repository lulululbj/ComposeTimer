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
package com.example.androiddevchallenge

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TimeViewModel : ViewModel() {

    var timeString by mutableStateOf("")

    fun start(time: Int) {
        viewModelScope.launch {
            startTimer(time)
                .collect {
                    Log.e("xxx", it.toString())
                    timeString = formatTime(it)
                }
        }
    }

    private fun startTimer(time: Int) =
        flow {
            for (i in time downTo 0) {
                emit(i)
                delay(1000)
            }
        }.flowOn(Dispatchers.Default)

    private fun formatTime(time: Int): String {

        var result = ""

        var hour = 0
        var min = 0
        var second = 0

        if (time <= 0) return "00:00"
        else {
            min = time / 60
            if (min < 60) {
                second = time % 60
                result = unitFormat(min) + ":" + unitFormat(second)
            } else {
                hour = min / 60
                if (hour > 99)
                    return "99:59:59"
                min %= 60
                second = time - hour * 3600 - min * 60
                result = unitFormat(hour) + ":" + unitFormat(min) + ":" + unitFormat(second)
            }
        }

        return result
    }

    private fun unitFormat(time: Int) = if (time in 0..9) "0$time" else time.toString()
}
