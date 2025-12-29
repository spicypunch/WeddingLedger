package kr.jm.weddingledger.core.di

import kr.jm.weddingledger.core.network.createSupabaseClient
import org.koin.dsl.module

val coreModule = module {
    single { createSupabaseClient() }
}
