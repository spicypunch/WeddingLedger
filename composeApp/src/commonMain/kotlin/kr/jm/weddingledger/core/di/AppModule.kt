package kr.jm.weddingledger.core.di

import kr.jm.weddingledger.feature.auth.di.authModule
import org.koin.core.module.Module

fun appModules(): List<Module> = listOf(
    coreModule,
    authModule
)
