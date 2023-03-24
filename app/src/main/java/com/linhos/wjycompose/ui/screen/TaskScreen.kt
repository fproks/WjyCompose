package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.linhos.wjycompose.ui.components.TopAppBar


@Preview
@Composable
fun TaskScreen(){
    Column {
        TopAppBar {
            Text("task app bar")
        }
        Text("this is task screen")
    }
}