package ucb.vargasn.project.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ucb.vargasn.project.catalog.presentation.viewmodel.CatalogViewModel

val presentationModule = module {

    viewModelOf(::CatalogViewModel)

}