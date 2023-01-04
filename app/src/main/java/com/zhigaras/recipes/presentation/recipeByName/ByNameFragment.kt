package com.zhigaras.recipes.presentation.recipeByName

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhigaras.recipes.R

class ByNameFragment : Fragment() {
    
    companion object {
        fun newInstance() = ByNameFragment()
    }
    
    private lateinit var viewModel: ByNameViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_by_name, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ByNameViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
}