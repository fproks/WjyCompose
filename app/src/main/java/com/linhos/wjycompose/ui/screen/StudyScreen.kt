package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linhos.wjycompose.ui.components.TopAppBar
import com.linhos.wjycompose.viewmodel.MainViewModel

@Composable
fun StudyScreen(viewModel: MainViewModel = viewModel()) {
    Column {

        StudystatusBar()

        CategoryTab(viewModel)
        TypesRowTab(viewModel)

    }
}


/*
      * 学习的标题栏
      * */
@Composable
fun StudystatusBar() {
    TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)) {
        Row {
            Surface(
                modifier = Modifier.clip(RoundedCornerShape(16.dp)).weight(1f), //圆角
                color = Color(0x33FFFFFF),  //30%透明度
                contentColor = Color.White  //内容白色
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically  //垂直居中
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = "搜索感兴趣的咨询或课程",
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "学习\n进度", fontSize = 10.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)


        }
    }
}

/*
* 分类目录
* */
@Composable
fun CategoryTab(viewModel: MainViewModel = viewModel()) {
    Row {
        TabRow(
            selectedTabIndex = viewModel.studyCategoryIndex,
            backgroundColor = Color(0x22149EE7),
            contentColor = Color(0xFF149EE7)
        ) {
            viewModel.categorys.forEachIndexed { index, category ->
                LeadingIconTab(
                    selected = index == viewModel.studyCategoryIndex,
                    onClick = { viewModel.updateStudyCategoryIndex(index) },
                    icon = { Icon(imageVector = category.icon, contentDescription = null) },
                    text = {
                        Text(
                            text = category.title,
                            fontSize = 14.sp
                        )
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666)
                )
            }
        }
    }
}

@Preview
@Composable
fun TypesRowTab(viewModel: MainViewModel = viewModel()) {
    Row {
        TabRow(
            selectedTabIndex = viewModel.typesIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color(0xFF149EE7),
            indicator = {},
            divider = {}  //分割线为空
        ) {
            viewModel.types.forEachIndexed { index, type ->
                LeadingIconTab(
                    selected = index == viewModel.typesIndex,
                    onClick = { viewModel.updateTypesIndex(index) },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                    icon = { Icon(type.icon, contentDescription = null) },
                    text = { Text(text = type.title, modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp) }
                )
            }
        }
    }
}


/*
* 类型选择Tab
* */
@Preview
@Composable
fun StudyScreenPreview() {
    StudyScreen()
}