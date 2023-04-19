package com.linhos.app.module.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.flow.MutableSharedFlow


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyWebView(state: MyWebViewState) {

    var webView by remember {
        mutableStateOf<WebView?>(null)
    }
    LaunchedEffect(webView, state) {
        with(state) {
            webView?.handleEvent()
        }
    }



    AndroidView(factory = { context ->
        WebView(context).apply {
            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    state.pageTitle = title
                }
            }
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    state.pageTitle = null
                }
            }
            with(settings) { javaScriptEnabled = true }
        }.also { webView = it }
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
        internal set

    private enum class EventType {
        EVALUATE_JAVASCRIPT  //执行JS方法
    }

    private class Event(val eventType: EventType, val args: String, val callback: ((String) -> Unit)?)

    //订阅流
    private val enevts: MutableSharedFlow<Event> = MutableSharedFlow()

    //获取流
    internal suspend fun WebView.handleEvent() {
        enevts.collect { event ->  //接收事件
            Log.d("===", "collect")
            when (event.eventType) {
                EventType.EVALUATE_JAVASCRIPT -> evaluateJavascript(
                    event.args,
                    event.callback
                )  //调用webview 的evaluateJavascript 函数
            }
        }
    }

    //把JavaScript 命令通过流的方式发过去
    suspend fun evaluateMyJavaScript(script: String, resultCallback: ((String) -> Unit)? = {}) {
        Log.d("===", "evaluateMyJavaScript: $script")
        val event = Event(EventType.EVALUATE_JAVASCRIPT, script, resultCallback)
        enevts.emit(event)  //发射事件
    }
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