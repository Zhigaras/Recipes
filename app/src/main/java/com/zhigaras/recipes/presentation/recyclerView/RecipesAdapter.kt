package com.zhigaras.recipes.presentation.recyclerView

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zhigaras.recipes.databinding.RecipeElementBinding
import com.zhigaras.recipes.entity.getRandom.Recipe

class RecipesAdapter : Adapter<RecipesAdapter.RecipeElementViewHolder>() {
    
    private var data: List<Recipe> = emptyList()
    
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Recipe>) {
        this.data = data
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeElementViewHolder {
        return RecipeElementViewHolder(
            RecipeElementBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    
    override fun onBindViewHolder(holder: RecipeElementViewHolder, position: Int) {
        val item = data.getOrNull(position)
        
        if (item != null) {
            holder.binding.apply {
                Glide.with(elementImage.context)
                    .load(Uri.parse(item.image))
                    .centerCrop()
                    .into(elementImage)
                
                elementTitle.text = item.title
                elementReadyMinutes.text = buildString {
                    append("Ready in ")
                    append(item.readyInMinutes)
                    append("min.")
                }
                elementServings.text = buildString {
                    append("Servings: ")
                    append(item.servings)
                }
            }
        }
    }
    
    override fun getItemCount(): Int = data.size
    
    class RecipeElementViewHolder(val binding: RecipeElementBinding) : ViewHolder(binding.root)
}