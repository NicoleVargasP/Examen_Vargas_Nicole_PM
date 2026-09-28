package ucb.vargasn.project.di

import org.koin.dsl.module
import ucb.vargasn.project.catalog.data.datasource.CatalogRemoteDataSource
import ucb.vargasn.project.catalog.data.repository.CatalogRepositoryImpl
import ucb.vargasn.project.catalog.data.service.CatalogService
import ucb.vargasn.project.catalog.domain.repository.CatalogRepository

val dataModule = module {

    single<CatalogRemoteDataSource> {
        CatalogService()
    }

    single<CatalogRepository> {
        CatalogRepositoryImpl(get())
    }

}