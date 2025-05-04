package com.example.clickawayapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val _items = mutableListOf<CartItem>()
    val cartItems: List<CartItem>
        get() = _items.toList()

    fun addItem(item: CartItem) {
        val existingItem = _items.find { it.name == item.name }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            _items.add(item)
        }
    }

    fun removeItem(itemName: String) {
        _items.removeAll { it.name == itemName }
    }

    fun clearCart() {
        _items.clear()
    }

    fun getTotalPrice(): Double {
        return _items.sumOf { it.price * it.quantity }
    }
}