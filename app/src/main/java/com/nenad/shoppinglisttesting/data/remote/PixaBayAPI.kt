package com.nenad.shoppinglisttesting.data.remote



import com.google.gson.internal.GsonBuildConfig
import com.nenad.shoppinglisttesting.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayAPI {
    @GET("/api/")
    suspend fun searchForImage(@Query ("q") searchQuery: String,
                               @Query("key") apiKey: String = "28726699-3ea6bf52fadd66ce4dac69907"
    ): Response<ImageResponse>



}