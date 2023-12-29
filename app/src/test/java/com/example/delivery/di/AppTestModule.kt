package com.example.delivery.di

import com.example.delivery.data.TestOrderRepository
import com.example.delivery.data.TestRestaurantFoodRepository
import com.example.delivery.data.TestRestaurantRepository
import com.example.delivery.data.entity.LocationLatLngEntity
import com.example.delivery.data.repository.order.OrderRepository
import com.example.delivery.data.repository.restaurant.RestaurantRepository
import com.example.delivery.data.repository.restaurant.food.RestaurantFoodRepository
import com.example.delivery.screen.main.home.restaurant.RestaurantCategory
import com.example.delivery.screen.main.home.restaurant.RestaurantListViewModel
import com.example.delivery.screen.order.OrderMenuListViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {
    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity)->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get())
    }

    viewModel { (firebaseAuth: FirebaseAuth) -> OrderMenuListViewModel(get(), get(), firebaseAuth) }

    single<RestaurantRepository> { TestRestaurantRepository() }

    single<RestaurantFoodRepository> { TestRestaurantFoodRepository() }

    single<OrderRepository> { TestOrderRepository() }

}