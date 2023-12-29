package com.example.delivery.widget.adapter.viewholder.order


import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.delivery.R
import com.example.delivery.databinding.ViewholderFoodMenuBinding
import com.example.delivery.databinding.ViewholderOrderBinding
import com.example.delivery.databinding.ViewholderOrderMenuBinding
import com.example.delivery.extensions.clear
import com.example.delivery.extensions.load
import com.example.delivery.model.restaurant.food.FoodModel
import com.example.delivery.model.restaurant.order.OrderModel
import com.example.delivery.screen.base.BaseViewModel
import com.example.delivery.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import com.example.delivery.util.provider.ResourcesProvider
import com.example.delivery.widget.adapter.listener.AdapterListener
import com.example.delivery.widget.adapter.listener.order.OrderListListener
import com.example.delivery.widget.adapter.listener.order.OrderMenuListListener
import com.example.delivery.widget.adapter.listener.restaurant.FoodMenuListListener
import com.example.delivery.widget.adapter.viewholder.ModelViewHolder

class OrderViewHolder(
    private val binding: ViewholderOrderBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<OrderModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = Unit

    override fun bindData(model: OrderModel) {
        super.bindData(model)
        with(binding) {
            orderTitleText.text =
                resourcesProvider.getString(R.string.order_history_title, model.orderId)
            val foodMenuList = model.foodMenuList
            foodMenuList.groupBy { it.title }
                .entries.forEach { (title, menuList) ->
                    val orderDataStr =
                        orderContentText.text.toString() + "메뉴 : $title | 가격 : ${menuList.first().price}원 X ${menuList.size}\n"
                    orderContentText.text = orderDataStr
                }
            orderContentText.text = orderContentText.text.trim()
            orderTotalPriceText.text = resourcesProvider.getString(
                R.string.price,
                foodMenuList.map { it.price }.reduce { total, price -> total + price })
        }
    }

    override fun bindViews(model: OrderModel, adapterListener: AdapterListener) {
        if (adapterListener is OrderListListener){
            binding.root.setOnClickListener {
                adapterListener.writeRestaurantReview(model.orderId, model.restaurantTitle)
            }
        }
    }

}