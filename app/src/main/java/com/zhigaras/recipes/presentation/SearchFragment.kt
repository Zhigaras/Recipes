package com.zhigaras.recipes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.zhigaras.recipes.R
import com.zhigaras.recipes.databinding.FragmentSearchBinding
import com.zhigaras.recipes.presentation.recyclerView.MarginItemDecoration
import com.zhigaras.recipes.presentation.recyclerView.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val searchViewModel: SearchViewModel by activityViewModels { viewModelFactory }
    
    private lateinit var recipeAdapter: RecipesAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setUpAdapter()
        
    }
    
    
    private fun setUpAdapter() {
        recipeAdapter = RecipesAdapter()
        binding.recyclerView.adapter = recipeAdapter
        binding.recyclerView.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.recycler_view_dimen
                )
            )
        )
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.randomRecipesFlow.collect {
                    recipeAdapter.setData(it)
                }
            }
        }
    }
    
//    private fun toStartFragmentListener() {
//        binding.toFirstButton.setOnClickListener {
//            findNavController().navigate(R.id.from_search_to_by_random)
//        }
//    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SearchFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SearchFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}