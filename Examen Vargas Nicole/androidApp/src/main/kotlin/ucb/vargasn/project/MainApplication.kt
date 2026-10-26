package ucb.vargasn.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ucb.vargasn.project.data.datasource.AndroidGrpcTestClient
import ucb.vargasn.project.domain.repository.GrpcTestClient
import ucb.vargasn.project.di.sharedModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                sharedModule() + module {
                    single<GrpcTestClient> { AndroidGrpcTestClient() }
                }
            )
        }
    }
}
