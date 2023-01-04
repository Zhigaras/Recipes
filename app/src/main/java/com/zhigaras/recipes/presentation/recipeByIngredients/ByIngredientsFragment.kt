package com.zhigaras.recipes.presentation.recipeByIngredients

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhigaras.recipes.R

class ByIngredientsFragment : Fragment() {
    
    companion object {
        fun newInstance() = ByIngredientsFragment()
    }
    
    private lateinit var viewModel: ByIngredientsViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_by_ingredients, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ByIngredientsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
}