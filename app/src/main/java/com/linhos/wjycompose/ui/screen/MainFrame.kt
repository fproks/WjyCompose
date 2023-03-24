package com.linhos.wjycompose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linhos.wjycompose.model.entity.NavigationItem
import com.linhos.wjycompose.viewmodel.MainViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFrame(mainViewModel: MainViewModel = viewModel()) {

    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "任务", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person)
    )

    var currentNavigationIndex by remember { mutableStateOf(0) }
    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.navigationBarsPadding()
        ) {
            navigationItems.forEachIndexed { index, navigationItem ->
                BottomNavigationItem(
                    selected = index == currentNavigationIndex,
                    onClick = { currentNavigationIndex = index },
                    icon = { Icon(imageVector = navigationItem.icon, contentDescription = null) },
                    label = { Text(navigationItem.title) },
                    alwaysShowLabel = false,
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xff999999)
                )
            }
        }
    }) {
        when (currentNavigationIndex) {
            0 -> StudyScreen()
            1 -> TaskScreen()
            2 -> MineScreen()
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}