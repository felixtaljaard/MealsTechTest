package com.example.mealdbapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealdbapp.data.entities.categories.Category
import com.example.mealdbapp.databinding.CategoryRowBinding

class CategoryAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Category, CategoryAdapter.MyViewHolderCategory>(CategoryItemDiffCallback()) {

    class OnClickListener(val clickListener: (category: Category) -> Unit) {
        fun onClick(category: Category) = clickListener(category)
    }

    class MyViewHolderCategory(val categoryRowBinding: CategoryRowBinding) :
        RecyclerView.ViewHolder(categoryRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCategory {
        val view = LayoutInflater.from(parent.context)
        val binding = CategoryRowBinding.inflate(view, parent, false)
        return MyViewHolderCategory(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolderCategory, position: Int) {
        val categoryList = getItem(position)

        holder.categoryRowBinding.textViewCategory.text = categoryList.strCategory

        Glide.with(holder.itemView.context)
            .load(categoryList.strCategoryThumb)
            .into(holder.categoryRowBinding.imageView)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(categoryList)
        }
    }
}

class CategoryItemDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem
}
