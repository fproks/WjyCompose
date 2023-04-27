package com.linhos.wjycompose

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.linhos.wjycompose.ui.screen.NaviHostApp
import com.linhos.wjycompose.ui.theme.WjyComposeTheme
import com.tencent.rtmp.TXLiveBase
import com.tencent.rtmp.TXLiveBaseListener

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val licenceURL = "https://license.vod2.myqcloud.com/license/v2/1312467715_1/v_cube.license"
        val licenseKEY = "ab389110d4f0b29803beaca0f52d11c5"
        TXLiveBase.getInstance().setLicence(this,licenceURL,licenseKEY)
        TXLiveBase.setListener(object:TXLiveBaseListener(){
            override fun onLicenceLoaded(result: Int, reason: String?) {
                Log.i("===", "onLicenceLoaded: result:$result, reason:$reason")
                super.onLicenceLoaded(result, reason)
            }
        })
        WindowCompat.setDecorFitsSystemWindows(window, false)//状态栏透明
        setContent {
            WjyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    //MainFrame()
                    NaviHostApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WjyComposeTheme {
        Greeting("Android")
    }
}
@Preview
@Composable
fun surface(){
    Surface{
        Text(text = "hello")
    }
}