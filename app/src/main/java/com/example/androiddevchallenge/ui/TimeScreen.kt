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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimeScreen(time: String, onStart: (Int) -> Unit) {

    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
        ) {
            AnimatedCircle(
                hasAnimation = time != "00:00" && time != "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White)
                    .size(320.dp),
                proportions = listOf(0.25f, 0.25f, 0.25f, 0.25f),
                colors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Black)
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = if (time == "") "00:00" else time,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        val textState = remember { mutableStateOf(TextFieldValue(text = "8")) }
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.h6,
        )

        Button(
            onClick = { onStart(textState.value.text.toInt()) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(48.dp),
        ) {
            Text("Start it !")
        }
    }
}

@Composable
@Preview
fun PreView() {
    TimeScreen(time = "00:00") {}
}
