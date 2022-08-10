package com.nenad.shoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import com.nenad.shoppinglisttesting.data.local.ShoppingDao
import com.nenad.shoppinglisttesting.data.local.ShoppingItem
import com.nenad.shoppinglisttesting.data.remote.PixaBayAPI
import com.nenad.shoppinglisttesting.data.remote.responses.ImageResponse
import com.nenad.shoppinglisttesting.other.Resource
import retrofit2.Response
import retrofit2.Response.error
import java.lang.Exception
import javax.inject.Inject
import kotlin.Result.Companion.success

class DefaultShoppingRepo @Inject constructor
    (private val shoppingDao: ShoppingDao,
     private val pixaBayAPI: PixaBayAPI) : ShoppingRepo {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
       shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
     return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixaBayAPI.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }




}