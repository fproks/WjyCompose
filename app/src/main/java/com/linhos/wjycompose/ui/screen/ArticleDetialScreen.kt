package com.linhos.wjycompose.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linhos.wjycompose.ui.components.MyWebView
import com.linhos.wjycompose.ui.components.rememberMyWebViewStateWithData
import com.linhos.wjycompose.viewmodel.ArticleViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleDetailScreen(articleViewModel: ArticleViewModel = viewModel(), onBack: () -> Unit) {

    var fontScale by remember { mutableStateOf(1.0f) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val myWebViewState = rememberMyWebViewStateWithData(articleViewModel.content)


    BottomSheetScaffold(scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(
                    "文章详情", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp
                )
            }, navigationIcon = {
                Icon(imageVector = Icons.Default.NavigateBefore,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onBack() //点击后触发返回事件
                    })
            }, actions = {
                Icon(
                    imageVector = Icons.Default.FormatSize,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            if (scaffoldState.bottomSheetState.isCollapsed) {
                                scaffoldState.bottomSheetState.expand()
                            } else scaffoldState.bottomSheetState.collapse()
                        }
                    }.padding(start = 16.dp, end = 8.dp)
                )
            })
        },
        modifier = Modifier.background(MaterialTheme.colors.primary).statusBarsPadding(),
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("字体大小")
                Slider(value = fontScale, onValueChange = {
                    Log.d("===", "fontSize change $it")
                    fontScale = it
                }, onValueChangeFinished = {
                    coroutineScope.launch {
                        myWebViewState.evaluateMyJavaScript("document.body.style.zoom = $fontScale")
                    }
                }, steps = 3, valueRange = 0.75f..1.75f)
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "较小",
                        modifier = Modifier.clickable { fontScale = 0.75f },
                        fontSize = 12.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        "标准",
                        modifier = Modifier.clickable { fontScale = 1.0f },
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        "较打",
                        modifier = Modifier.clickable { fontScale = 1.25f },
                        fontSize = 16.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        "特大",
                        modifier = Modifier.clickable { fontScale = 1.5f },
                        fontSize = 18.sp,
                        color = Color(0xFF999999)
                    )
                    Text(
                        "超大",
                        modifier = Modifier.clickable { fontScale = 1.75f },
                        fontSize = 20.sp,
                        color = Color(0xFF999999)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }) {

        //Text("这是文章详情")
        //val state = rememberWebViewStateWithHTMLData("<h1> wenView</h1>")
        //WebView(state)
        MyWebView(myWebViewState)


    }
}