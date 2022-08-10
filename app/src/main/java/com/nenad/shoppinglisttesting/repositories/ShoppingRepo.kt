package com.nenad.shoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import com.nenad.shoppinglisttesting.data.local.ShoppingItem
import com.nenad.shoppinglisttesting.data.remote.responses.ImageResponse
import com.nenad.shoppinglisttesting.data.remote.responses.ImageResult
import com.nenad.shoppinglisttesting.other.Resource
import retrofit2.Response

interface ShoppingRepo {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems() : LiveData<List<ShoppingItem>>

    fun observeTotalPrice():LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>



}