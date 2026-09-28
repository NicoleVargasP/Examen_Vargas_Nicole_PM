package ucb.vargasn.project.di

import org.koin.dsl.module
import ucb.vargasn.project.earthquakes_examen.domain.usecase.GetEarthquakesUseCase

val domainModule = module {

    factory {
        GetEarthquakesUseCase(get())
    }

}