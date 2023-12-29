package com.example.delivery.util.event

import com.example.delivery.screen.main.MainTavMenu
import kotlinx.coroutines.flow.MutableSharedFlow

class MenuChangeEventBus {

    val mainTabMenuFlow = MutableSharedFlow<MainTavMenu>()

    suspend fun changeMenu(menu: MainTavMenu){
        mainTabMenuFlow.emit(menu)
    }

}