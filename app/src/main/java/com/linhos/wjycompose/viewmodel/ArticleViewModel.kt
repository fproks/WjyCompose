package com.linhos.wjycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.ArticleEntity

class ArticleViewModel : ViewModel() {
     val list = listOf(

        ArticleEntity(
            title = "新闻列表title 1 新闻列表title 1 新闻列表title 1",
            source = "新闻来源",
            timestamp = "2020-02-10"
        ),
        ArticleEntity(
            title = "新闻列表title 2 新闻列表title 2 新闻列表title 2",
            source = "新闻来源1",
            timestamp = "2020-02-11"
        ),
        ArticleEntity(
            title = "新闻列表title 3 新闻列表title 3 新闻列表title 3",
            source = "新闻来源3",
            timestamp = "2020-02-12"
        ),
         ArticleEntity(
             title = "新闻列表title 1 新闻列表title 1 新闻列表title 1",
             source = "新闻来源",
             timestamp = "2020-02-10"
         ),
         ArticleEntity(
             title = "新闻列表title 2 新闻列表title 2 新闻列表title 2",
             source = "新闻来源1",
             timestamp = "2020-02-11"
         ),
         ArticleEntity(
             title = "新闻列表title 3 新闻列表title 3 新闻列表title 3",
             source = "新闻来源3",
             timestamp = "2020-02-12"
         )
    )

}