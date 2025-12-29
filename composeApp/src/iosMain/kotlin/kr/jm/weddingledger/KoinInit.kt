package kr.jm.weddingledger

import kr.jm.weddingledger.core.di.appModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModules())
    }
}
