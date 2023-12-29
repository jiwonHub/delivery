package com.example.delivery.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import com.example.delivery.databinding.ViewholderEmptyBinding
import com.example.delivery.databinding.ViewholderFoodMenuBinding
import com.example.delivery.databinding.ViewholderOrderBinding
import com.example.delivery.databinding.ViewholderOrderMenuBinding
import com.example.delivery.databinding.ViewholerLikeRestaurantBinding
import com.example.delivery.databinding.ViewholerRestaurantBinding
import com.example.delivery.databinding.ViewholerRestaurantReviewBinding
import com.example.delivery.model.CellType
import com.example.delivery.model.Model
import com.example.delivery.screen.base.BaseViewModel
import com.example.delivery.util.provider.ResourcesProvider
import com.example.delivery.widget.adapter.viewholder.EmptyViewHolder
import com.example.delivery.widget.adapter.viewholder.ModelViewHolder
import com.example.delivery.widget.adapter.viewholder.food.FoodMenuViewHolder
import com.example.delivery.widget.adapter.viewholder.order.OrderMenuViewHolder
import com.example.delivery.widget.adapter.viewholder.order.OrderViewHolder
import com.example.delivery.widget.adapter.viewholder.restaurant.LikeRestaurantViewHolder
import com.example.delivery.widget.adapter.viewholder.restaurant.RestaurantViewHolder
import com.example.delivery.widget.adapter.viewholder.review.RestaurantReviewViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun<M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M>{
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when(type){
            CellType.EMPTY_CELL -> EmptyViewHolder(
                ViewholderEmptyBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.RESTAURANT_CELL -> RestaurantViewHolder(
                ViewholerRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.LIKE_RESTAURANT_CELL -> LikeRestaurantViewHolder(
                ViewholerLikeRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.FOOD_CELL -> FoodMenuViewHolder(
                ViewholderFoodMenuBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.REVIEW_CELL -> RestaurantReviewViewHolder(
                ViewholerRestaurantReviewBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.ORDER_FOOD_CELL -> {
                OrderMenuViewHolder(
                    ViewholderOrderMenuBinding.inflate(inflater, parent, false),
                    viewModel,
                    resourcesProvider
                )
            }
            CellType.ORDER_CELL -> OrderViewHolder(
                ViewholderOrderBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
        }
        return viewHolder as ModelViewHolder<M>
    }

}