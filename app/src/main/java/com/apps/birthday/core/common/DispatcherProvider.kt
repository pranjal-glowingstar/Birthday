package com.apps.birthday.core.common

import kotlinx.coroutines.Dispatchers

object DispatcherProvider {

    private val IO = Dispatchers.IO
    private val Main = Dispatchers.Main
    private val Default = Dispatchers.Default

    fun getIoDispatcher() = IO
}