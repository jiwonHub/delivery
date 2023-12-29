package com.example.delivery.widget.adapter.viewholder.order


import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.delivery.R
import com.example.delivery.databinding.ViewholderFoodMenuBinding
import com.example.delivery.databinding.ViewholderOrderMenuBinding
import com.example.delivery.extensions.clear
import com.example.delivery.extensions.load
import com.example.delivery.model.restaurant.food.FoodModel
import com.example.delivery.screen.base.BaseViewModel
import com.example.delivery.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import com.example.delivery.util.provider.ResourcesProvider
import com.example.delivery.widget.adapter.listener.AdapterListener
import com.example.delivery.widget.adapter.listener.order.OrderMenuListListener
import com.example.delivery.widget.adapter.listener.restaurant.FoodMenuListListener
import com.example.delivery.widget.adapter.viewholder.ModelViewHolder

class OrderMenuViewHolder(
    private val binding: ViewholderOrderMenuBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<FoodModel>(binding, viewModel, resourcesProvider) {
    override fun reset() = with(binding){
        foodImage.clear()
    }

    override fun bindData(model: FoodModel) {
        super.bindData(model)
        with(binding){
            foodImage.load(model.imageUrl, 24f, CenterCrop())
            foodTitleText.text = model.title
            foodDescriptionText.text = model.description
            priceText.text = resourcesProvider.getString(R.string.price, model.price)
        }
    }

    override fun bindViews(model: FoodModel, adapterListener: AdapterListener) {
        if (adapterListener is OrderMenuListListener){
            binding.removeButton.setOnClickListener {
                adapterListener.onRemoveItem(model)
            }
        }
    }


}