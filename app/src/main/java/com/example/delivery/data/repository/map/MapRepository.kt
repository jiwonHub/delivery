package com.example.delivery.data.repository.map

import com.example.delivery.data.entity.LocationLatLngEntity
import com.example.delivery.data.response.address.AddressInfo

interface MapRepository {

    suspend fun getReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity): AddressInfo?

}