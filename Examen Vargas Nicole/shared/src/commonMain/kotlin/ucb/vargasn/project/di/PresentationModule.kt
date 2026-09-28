package ucb.vargasn.project.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ucb.vargasn.project.earthquakes_examen.presentation.viewmodel.EarthquakeViewModel

val presentationModule = module {

    viewModelOf(::EarthquakeViewModel)

}