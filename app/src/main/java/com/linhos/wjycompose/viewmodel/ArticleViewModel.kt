package com.linhos.wjycompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.ArticleEntity
import com.linhos.wjycompose.model.service.HomeService

class ArticleViewModel : ViewModel() {
    private val pageSize = 10
    private var pageOffset = 1
    var list by mutableStateOf(
        listOf(
            ArticleEntity(
                title = "新闻列表title 1 新闻列表title 1 新闻列表title 1",
                source = "新闻来源",
                timestamp = "2020-02-10"
            )
        )
    )
        private set
    var fetachLoaded by mutableStateOf(false)
        private set

    suspend fun fetchArticleList() {
        val res = HomeService.instance().articleList(pageOffset = pageOffset, pageSize = pageSize)
        if (res.code == 0 && res.data != null) {
            list = res.data
            fetachLoaded = true
        }
    }

    suspend fun refresh() {
        pageOffset += 1
        fetchArticleList()
        if (list.size < pageSize) {
            pageOffset = 0
        }
    }

    suspend fun fetchArticleDetail() {
        val res = HomeService.instance().articleDetail("")
        if (res.code == 0 && res.data != null) {
            content = """$head
                ${res.data.content}
                $end
            """.trimMargin()
        }
    }


    var content by mutableStateOf("")


    val head = """
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="content-language" content="zh-CN">
	<meta http-equiv="Cache-Control"  content="no-transform" />
	<meta http-equiv="Cache-Control"  content="no-siteapp" />
	<meta name="applicable-device" content="pc">
	<meta name="mobile-agent" content="format=xhtml;url=https://m.guancha.cn/internation/2023_04_10_687737.shtml">
	<link rel="alternate" media="only screen and (max-width: 970px)"     href="https://m.guancha.cn/internation/2023_04_10_687737.shtml" >
	<meta name="mobile-agent" content="format=html5;url=https://m.guancha.cn/internation/2023_04_10_687737.shtml">
    <meta name="Keywords" content="">
       <link href="https://i.guancha.cn/images/favorite.ico" rel="shortcut icon"/>
   
    <style>img{
    max-width:100% !important
    }</style>
</head>
<body>""".trimIndent()
    val end = """
        </body>
        </html>""".trimIndent()


}