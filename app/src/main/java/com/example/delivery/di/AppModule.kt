package com.example.delivery.di

import com.example.delivery.data.entity.LocationLatLngEntity
import com.example.delivery.data.entity.MapSearchInfoEntity
import com.example.delivery.data.entity.RestaurantEntity
import com.example.delivery.data.entity.restaurant.RestaurantFoodEntity
import com.example.delivery.data.preference.AppPreferenceManager
import com.example.delivery.data.repository.map.DefaultMapRepository
import com.example.delivery.data.repository.map.MapRepository
import com.example.delivery.data.repository.order.DefaultOrderRepository
import com.example.delivery.data.repository.order.OrderRepository
import com.example.delivery.data.repository.restaurant.DefaultRestaurantRepository
import com.example.delivery.data.repository.restaurant.RestaurantRepository
import com.example.delivery.data.repository.restaurant.food.DefaultRestaurantFoodRepository
import com.example.delivery.data.repository.restaurant.food.RestaurantFoodRepository
import com.example.delivery.data.repository.restaurant.review.DefaultRestaurantReviewRepository
import com.example.delivery.data.repository.restaurant.review.RestaurantReviewRepository
import com.example.delivery.data.repository.user.DefaultUserRepository
import com.example.delivery.data.repository.user.UserRepository
import com.example.delivery.screen.main.home.HomeViewModel
import com.example.delivery.screen.main.home.restaurant.RestaurantCategory
import com.example.delivery.screen.main.home.restaurant.RestaurantListViewModel
import com.example.delivery.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import com.example.delivery.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import com.example.delivery.screen.main.home.restaurant.detail.review.RestaurantReviewListViewModel
import com.example.delivery.screen.main.like.RestaurantLikeListViewModel
import com.example.delivery.screen.main.my.MyViewModel
import com.example.delivery.screen.mylocation.MyLocationViewModel
import com.example.delivery.screen.order.OrderMenuListViewModel
import com.example.delivery.screen.review.gallery.GalleryPhotoRepository
import com.example.delivery.util.event.MenuChangeEventBus
import com.example.delivery.util.provider.ResourcesProvider
import com.example.delivery.util.provider.DefaultResourceProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { MyViewModel(get(), get(), get()) }
    viewModel { RestaurantLikeListViewModel(get()) }
    viewModel { (firebaseAuth: FirebaseAuth) -> OrderMenuListViewModel(get(), get(), firebaseAuth) }

    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get())
    }
    viewModel { (MapSearchInfoEntity: MapSearchInfoEntity) ->
        MyLocationViewModel(
            MapSearchInfoEntity,
            get(),
            get()
        )
    }
    viewModel { (restaurantEntity: RestaurantEntity) ->
        RestaurantDetailViewModel(
            restaurantEntity,
            get(),
            get()
        )
    }
    viewModel { (restaurantId: Long, restaurantFoodList: List<RestaurantFoodEntity>) ->
        RestaurantMenuListViewModel(
            restaurantId,
            restaurantFoodList,
            get()
        )
    }
    viewModel { (restaurantTitle: String) -> RestaurantReviewListViewModel(restaurantTitle, get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get(), get()) }
    single<RestaurantFoodRepository> { DefaultRestaurantFoodRepository(get(), get(), get()) }
    single<OrderRepository> { DefaultOrderRepository(get(), get()) }
    single<RestaurantReviewRepository> { DefaultRestaurantReviewRepository(get(), get()) }
    single { GalleryPhotoRepository(androidApplication()) }

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single(named("food")) { provideFoodRetrofit(get(), get()) }

    single { provideMapApiService(get(qualifier = named("map"))) }
    single { provideFoodApiService(get(qualifier = named("food"))) }

    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
    single { provideRestaurantDao(get()) }
    single { provideFoodMenuBasketDao(get()) }

    single<ResourcesProvider> { DefaultResourceProvider(androidApplication()) }
    single { AppPreferenceManager(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single { MenuChangeEventBus() }

    single { Firebase.firestore }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }
}