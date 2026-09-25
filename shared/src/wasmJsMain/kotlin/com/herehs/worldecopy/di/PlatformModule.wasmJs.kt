package com.herehs.worldecopy.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.herehs.worldecopy.data.local.util.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DataStore<Preferences>> {
        createDataStore()
    }
}