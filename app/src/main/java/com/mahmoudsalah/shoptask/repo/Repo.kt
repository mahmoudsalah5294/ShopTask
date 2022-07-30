package com.mahmoudsalah.shoptask.repo

import com.mahmoudsalah.shoptask.model.ItemModel
import retrofit2.Response

class Repo:RepoInterface {
    override suspend fun getItems(requestType:String): Response<ItemModel> {
        return APIService.getAPIService().getItems(requestType)
    }
}