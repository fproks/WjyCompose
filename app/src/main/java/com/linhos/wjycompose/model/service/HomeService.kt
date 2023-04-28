package com.linhos.wjycompose.model.service

import com.linhos.wjycompose.model.Network
import com.linhos.wjycompose.model.entity.Category
import retrofit2.http.GET

interface HomeService {
    @GET("category/list")
    suspend fun category(): CategoryResponse

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