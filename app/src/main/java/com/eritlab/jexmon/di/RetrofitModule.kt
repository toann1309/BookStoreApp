package com.eritlab.jexmon.di

import com.eritlab.jexmon.domain.repository.BookStoreAPI
import com.eritlab.jexmon.utils.ConstantsBookStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
                .header("Origin", ConstantsBookStore.BASE_URL)
                .header("Access-Control-Allow-Origin", ConstantsBookStore.BASE_URL)
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Authorization, Content-Type")
                .method(originalRequest.method, originalRequest.body)
                .build()
            chain.proceed(request)
        }
        .build()
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantsBookStore.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideBookStoreApi(retrofit: Retrofit): BookStoreAPI {
        return retrofit.create(BookStoreAPI::class.java)
    }
}