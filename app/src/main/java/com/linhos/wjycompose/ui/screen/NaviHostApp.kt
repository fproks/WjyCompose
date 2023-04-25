package com.linhos.wjycompose.ui.screen


import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.linhos.wjycompose.ui.navigation.Desionations
import com.linhos.wjycompose.viewmodel.ActivateUserViewModel
import com.linhos.wjycompose.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NaviHostApp() {
    val TAG = "NAVI_HOST"
    val navController = rememberAnimatedNavController()
    val context: Context = LocalContext.current

    CompositionLocalProvider(ActivateUserViewModel provides UserViewModel()) {

        val userViewModel = ActivateUserViewModel.current

        //带动画的导航
        AnimatedNavHost(
            navController = navController,
            startDestination = Desionations.HomeFrame.route
        ) {
            Log.d("===", "st")
            //必须使用 com.google.accompanist.navigation.animation.composable 的 composable
            composable(Desionations.HomeFrame.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Up)//上滑进入
                }) { //这个路由，跳转到这个里面的函数里
                MainFrame(onNavigateToArticle = {  //导航到article 的路由
                    navController.navigate(Desionations.ArticleDetail.route)
                }, onNavigateToVideo = { //导航到video的路由
                    navController.navigate(Desionations.VideoDetail.route)
                }, onNavigateToHistory = {
                    if (!userViewModel.logged)
                        navController.navigate(Desionations.Login.route)
                })
            }
            composable(Desionations.ArticleDetail.route, enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
            }) { //这个路由，跳转到这里面来
                ArticleDetailScreen(onBack = { navController.popBackStack() })//点击后返回
            }

            //视频导航
            composable(Desionations.VideoDetail.route, enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Down)
            }) {
                VideoDetailScreen(onBack = { navController.popBackStack() })
            }
            composable(Desionations.Login.route, enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
            }) {
                LoginScreen(onBack = { navController.popBackStack() })
            }

        }
    }


}