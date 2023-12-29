package com.example.delivery.widget.adapter.listener.order

import com.example.delivery.model.restaurant.food.FoodModel
import com.example.delivery.widget.adapter.listener.AdapterListener

interface OrderMenuListListener: AdapterListener {

    fun onRemoveItem(foodModel: FoodModel)

}