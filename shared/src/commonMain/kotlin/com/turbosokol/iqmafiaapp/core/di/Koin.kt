package com.turbosokol.iqmafiaapp.core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    //modules
}

val storeModule = module {
    single<> {  }
}