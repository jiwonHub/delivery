package com.example.delivery.data.entity.restaurant

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.example.delivery.data.entity.Entity
import kotlinx.parcelize.Parcelize


@Parcelize
@androidx.room.Entity
data class RestaurantFoodEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val restaurantId: Long,
    val restaurantTitle : String
) : Parcelable
