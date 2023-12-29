package com.example.delivery.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.delivery.data.entity.LocationLatLngEntity
import com.example.delivery.screen.main.home.restaurant.RestaurantListFragment

class RestaurantDetailListFragmentPagerAdapter(
    val activity: FragmentActivity,
    val fragmentList: List<Fragment>,
):FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]



}