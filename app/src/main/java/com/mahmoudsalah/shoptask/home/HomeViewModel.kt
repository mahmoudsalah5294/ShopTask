package com.mahmoudsalah.shoptask.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudsalah.shoptask.model.ItemModel
import com.mahmoudsalah.shoptask.repo.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repo:RepoInterface, private val requestType:String) : ViewModel() {
    val mutableItemData = MutableLiveData<ItemModel>()
    val errorLiveData = MutableLiveData<String>()

    fun getItems(){
        viewModelScope.launch {
            val result = repo.getItems(requestType)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    mutableItemData.postValue(result.body())
                }else{
                    errorLiveData.postValue("Error ${result.errorBody()}")
                }
            }
        }
    }
}