package com.linhos.wjycompose.model.service

import com.linhos.wjycompose.model.Network
import com.linhos.wjycompose.model.entity.ArticleEntity
import com.linhos.wjycompose.model.entity.Category
import com.linhos.wjycompose.model.entity.SwiperEntity
import com.linhos.wjycompose.model.entity.VideoEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("category/list")
    suspend fun category(): CategoryResponse

    @GET("recommand/banner")
    suspend fun banner(): SwiperResponse

    @GET("article/list")
    suspend fun articleList(
        @Query("pageOffset") pageOffset: Int,
        @Query("pageSize") pageSize: Int
    ): ArticleResponse

    @GET("video/list")
    suspend fun videoList(
        @Query("pageOffset") pageOffset: Int,
        @Query("pageSize") pageSize: Int
    ): VideoListResponse

    @GET("article/info")
    suspend fun articleDetail(
        @Query("id") id:String
    ):ArticleDetailResponse
    @GET("video/info")
    suspend fun videoInfo(
        @Query("id") id:String
    ):VideoInfoResponse


    companion object {
        private var INSTANCE: HomeService? = null
        fun instance(): HomeService {
            synchronized(this) {
                if (INSTANCE == null)
                    INSTANCE = Network.createService(HomeService::class.java)
                return INSTANCE!!
            }

        }
    }
}


data class CategoryResponse(var data: List<Category>) : BaseResponse()

//轮播图数据
data class SwiperResponse(val data: List<SwiperEntity>?) : BaseResponse()

data class ArticleResponse(val data: List<ArticleEntity>?) : BaseResponse()
data class VideoListResponse(val data: List<VideoEntity>?) : BaseResponse()
data class ArticleDetailResponse(val data: ArticleEntity?):BaseResponse()
data class VideoInfoResponse(val data:VideoEntity?):BaseResponse()