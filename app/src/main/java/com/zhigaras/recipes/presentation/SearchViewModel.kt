package com.zhigaras.recipes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhigaras.recipes.domain.GetRecipesUseCase
import com.zhigaras.recipes.entity.getRandom.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {
    
    private val _randomRecipesFlow = MutableStateFlow<List<Recipe>>(emptyList())
    val randomRecipesFlow = _randomRecipesFlow.asStateFlow()

//    private val _randomRecipesChannel = Channel<List<Recipe>>()
//    val randomRecipesChannel = _randomRecipesChannel.receiveAsFlow()
    
    fun getRandomRecipe(recipesNumber: Int, dishType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getRecipesUseCase.fetchRandomRecipe(recipesNumber, dishType)
            }.fold(
                onSuccess = { _randomRecipesFlow.value = it },
                onFailure = {}
            )
        }
    }
}