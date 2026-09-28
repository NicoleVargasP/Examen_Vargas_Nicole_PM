package ucb.vargasn.project.di

import org.koin.dsl.module
import ucb.vargasn.project.catalog.domain.usecase.GetMoviesUseCase

val domainModule = module {

    factory {
        GetMoviesUseCase(get())
    }

}