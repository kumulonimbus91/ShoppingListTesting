package com.nenad.shoppinglisttesting.DI

import android.content.Context
import androidx.room.Room
import com.nenad.shoppinglisttesting.data.local.ShoppingDao
import com.nenad.shoppinglisttesting.data.local.ShoppingItemDatabase
import com.nenad.shoppinglisttesting.data.remote.PixaBayAPI
import com.nenad.shoppinglisttesting.other.Constants.BASE_URL
import com.nenad.shoppinglisttesting.other.Constants.DATABASE_NAME
import com.nenad.shoppinglisttesting.repositories.DefaultShoppingRepo
import com.nenad.shoppinglisttesting.repositories.ShoppingRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideShoppingItemDatabase(@ApplicationContext context: Context)
    = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: ShoppingItemDatabase) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixaBayAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
            BASE_URL).build().create(PixaBayAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideDefaultShoppingRepo(dao: ShoppingDao, api: PixaBayAPI) = DefaultShoppingRepo(dao, api) as ShoppingRepo
}





