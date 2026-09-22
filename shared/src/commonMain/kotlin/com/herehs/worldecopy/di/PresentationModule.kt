package com.herehs.worldecopy.di

import com.herehs.worldecopy.presentation.screens.registration.RegistrationViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val presentationModule = module {

    viewModel<RegistrationViewModel>()
}