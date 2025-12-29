package kr.jm.weddingledger

import android.app.Application
import kr.jm.weddingledger.core.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeddingLedgerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeddingLedgerApp)
            modules(appModules())
        }
    }
}
