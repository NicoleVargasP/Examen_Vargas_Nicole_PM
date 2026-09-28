package ucb.vargasn.project.di

import org.koin.dsl.module
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

}