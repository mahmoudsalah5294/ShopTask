package com.mahmoudsalah.shoptask.repo

import com.mahmoudsalah.shoptask.model.ItemModel
import retrofit2.Response

interface RepoInterface {
    suspend fun getItems(requestType:String):Response<ItemModel>
}