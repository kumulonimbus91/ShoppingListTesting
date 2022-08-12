package com.nenad.shoppinglisttesting.ui

import android.app.usage.UsageEvents
import android.media.metrics.Event
import android.util.EventLog
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nenad.shoppinglisttesting.data.local.ShoppingItem
import com.nenad.shoppinglisttesting.data.remote.responses.ImageResponse
import com.nenad.shoppinglisttesting.other.Resource
import com.nenad.shoppinglisttesting.repositories.ShoppingRepo
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepo
) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<com.nenad.shoppinglisttesting.other.Event<Resource<ImageResponse>>>()
    val images: LiveData<com.nenad.shoppinglisttesting.other.Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<com.nenad.shoppinglisttesting.other.Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<com.nenad.shoppinglisttesting.other.Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {

    }

    fun searchForImage(imageQuery: String) {

    }
}


