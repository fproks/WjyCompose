package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.linhos.wjycompose.ui.components.TopAppBar

@Preview
@Composable
fun MineScreen(){
    Column (modifier = Modifier.fillMaxWidth()) {
        TopAppBar {
            Text("my app bar")
        }
        Text("my page")
    }
}