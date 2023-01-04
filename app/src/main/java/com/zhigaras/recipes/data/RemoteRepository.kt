package com.zhigaras.recipes.data

import com.zhigaras.recipes.entity.getRandom.RandomRecipeList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject


private const val API_KEY = "61bec4a3a87041b9b88d383da578b4d6"
private const val BASE_URL = "https://api.spoonacular.com/"

interface RecipesInterface {
    
    @Headers("X-API-KEY: $API_KEY")
    @GET("recipes/random")
    suspend fun findRandomRecipe(
        @Query("number") number: Int,
        @Query("tags") dishType: String
    ): RandomRecipeList
}

class RemoteRepository @Inject constructor() {
    
    private val retrofit = Retrofit
        .Builder()
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val recipesApi: RecipesInterface = retrofit.create(RecipesInterface::class.java)
}