package nstv.compositevmchannel

import android.app.Application
import nstv.compositevmchannel.di.AppComponent

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponent().initModules(this)
    }
}
