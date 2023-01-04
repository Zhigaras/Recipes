package com.zhigaras.recipes.presentation.recipeByRandom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zhigaras.recipes.R
import com.zhigaras.recipes.databinding.FragmentByRandomBinding
import com.zhigaras.recipes.presentation.SearchViewModel
import com.zhigaras.recipes.presentation.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ByRandomFragment : Fragment() {
    
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val searchViewModel: SearchViewModel by activityViewModels { viewModelFactory }
    
    private var _binding: FragmentByRandomBinding? = null
    private val binding get() = _binding!!
    
    private val numberList = listOf(5, 10, 15, 20, 25, 30)
    private val dishTypes = listOf(
        "antipasti",
        "condiment",
        "starter",
        "snack",
        "vegetarian",
        "dessert",
        "appetizer",
        "dip",
        "antipasto",
        "hor d'oeuvre",
        "spread"
    )
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentByRandomBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val numberSpinnerAdapter: ArrayAdapter<Int> = ArrayAdapter(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            numberList
        )
        val dishTypesSpinnerAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            dishTypes
        )
        
        binding.numberSpinner.adapter = numberSpinnerAdapter
        binding.dishTypeSpinner.adapter = dishTypesSpinnerAdapter
        
        binding.getRecipesButton.setOnClickListener {
            searchViewModel.getRandomRecipe(
                binding.numberSpinner.selectedItem.toString().toInt(),
                binding.dishTypeSpinner.selectedItem.toString()
            
            )
            findNavController().navigate(R.id.from_by_random_to_search)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        fun newInstance() = ByRandomFragment()
    }
}