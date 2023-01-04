package com.zhigaras.recipes.domain

import com.zhigaras.recipes.data.RemoteRepository
import com.zhigaras.recipes.entity.getRandom.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    suspend fun fetchRandomRecipe(recipesNumber: Int, dishType: String): List<Recipe> {
        return remoteRepository.recipesApi.findRandomRecipe(recipesNumber, dishType).recipes
    }

}