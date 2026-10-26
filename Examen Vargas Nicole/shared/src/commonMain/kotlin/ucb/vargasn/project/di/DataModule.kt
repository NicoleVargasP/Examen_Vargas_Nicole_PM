package ucb.vargasn.project.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ucb.vargasn.project.domain.repository.GrpcTestClient
import ucb.vargasn.project.earthquakes_examen.data.datasource.EarthquakeRemoteDataSource
import ucb.vargasn.project.earthquakes_examen.data.repository.EarthquakeRepositoryImpl
import ucb.vargasn.project.earthquakes_examen.data.service.EarthquakeService
import ucb.vargasn.project.earthquakes_examen.domain.repository.EarthquakeRepository

val dataModule = module {

    single<EarthquakeRemoteDataSource> {
        EarthquakeService()
    }

    single<EarthquakeRepository> {
        EarthquakeRepositoryImpl(get())
    }

    single<GrpcTestClient> {
        object : GrpcTestClient {
            override suspend fun sendTestLog(): Boolean = false
        }
    }

}

expect val databaseModule: Module
