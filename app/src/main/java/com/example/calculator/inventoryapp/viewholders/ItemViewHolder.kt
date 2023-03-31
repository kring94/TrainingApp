package com.example.calculator.inventoryapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemListItemBinding
import com.example.calculator.inventoryapp.data.Item
import com.example.calculator.inventoryapp.data.getFormattedPrice

class ItemViewHolder(private var binding: ItemListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.apply {
            itemName.text = item.itemName
            itemPrice.text = item.getFormattedPrice()
            itemQuantity.text = item.quantityInStock.toString()
        }
    }
}