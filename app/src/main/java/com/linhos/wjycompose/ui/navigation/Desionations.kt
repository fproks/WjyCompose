package com.linhos.wjycompose.ui.navigation

sealed class Desionations(val route: String) {
    //home 主页
    object HomeFrame : Desionations("HomeFrame")

    //文章详情页
    object ArticleDetail : Desionations("ArticleDetail")

    object VideoDetail: Desionations("VideoDetail")
    object Login:Desionations("Login")
}
