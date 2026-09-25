package com.herehs.worldecopy.di

import com.herehs.worldecopy.AppViewModel
import com.herehs.worldecopy.presentation.screens.authorisation.AuthorisationViewModel
import com.herehs.worldecopy.presentation.screens.leaderboard.LeaderBoardViewModel
import com.herehs.worldecopy.presentation.screens.main.MainScreenViewModel
import com.herehs.worldecopy.presentation.screens.registration.RegistrationViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val presentationModule = module {
    viewModel<RegistrationViewModel>()
    viewModel<AuthorisationViewModel>()
    viewModel<MainScreenViewModel>()
    viewModel<LeaderBoardViewModel>()
    viewModel<AppViewModel>()
}