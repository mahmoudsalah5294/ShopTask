package com.mahmoudsalah.shoptask.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmoudsalah.shoptask.repo.RepoInterface

class ViewModelFactory(val repo:RepoInterface,val requestType:String):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo,requestType) as T
    }
}