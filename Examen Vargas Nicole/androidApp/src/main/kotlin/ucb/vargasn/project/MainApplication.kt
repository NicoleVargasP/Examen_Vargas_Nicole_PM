package ucb.vargasn.project

import android.app.Application
import ucb.vargasn.project.di.initKoinAndroid

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoinAndroid(this)
    }
}