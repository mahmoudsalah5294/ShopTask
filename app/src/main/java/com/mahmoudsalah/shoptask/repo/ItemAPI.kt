package com.mahmoudsalah.shoptask.repo

import com.mahmoudsalah.shoptask.model.ItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ItemAPI {
    @GET
    suspend fun getItems(@Url url:String):Response<ItemModel>
}