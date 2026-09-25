package com.herehs.blackhxle.di

import com.herehs.worldecopy.di.dataModule
import com.herehs.worldecopy.di.domainModule
import com.herehs.worldecopy.di.platformModule
import com.herehs.worldecopy.di.presentationModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config: KoinAppDeclaration? = null): KoinApplication {
    return startKoin {
        includes(config)
        modules(
            domainModule,
            dataModule,
            presentationModule,
            platformModule
        )
    }
}