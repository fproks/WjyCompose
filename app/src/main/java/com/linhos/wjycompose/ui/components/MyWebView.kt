package com.linhos.wjycompose.ui.components

import android.webkit.WebView
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun MyWebView(state: MyWebViewState) {
    AndroidView(factory = { context ->
        WebView(context)
    }) { view ->
        when (val content = state.content) {
            is WebContent.Url -> {
                if (content.url.isNotEmpty() && content.url != view.url)
                    view.loadUrl(content.url)
            }

            is WebContent.Data -> view.loadData(content.data, null, "utf-8")
        }


    }
}

//设定存储类型
sealed class WebContent() {
    data class Url(val url: String) : WebContent()
    data class Data(val data: String) : WebContent()
}

//设定存储数据
class MyWebViewState(webContent: WebContent, pageTitle: String? = null) {
    var content by mutableStateOf(webContent)
    var pageTitle: String? by mutableStateOf(pageTitle)
}


//不同的存储类型创建不同的remember
@Composable
fun rememberMyWebViewStateWithURL(url: String) = remember(key1 = url) {
    MyWebViewState(WebContent.Url(url))
}

@Composable
fun rememberMyWebViewStateWithData(data: String) = remember(key1 = data) {
    MyWebViewState(WebContent.Data(data))
}