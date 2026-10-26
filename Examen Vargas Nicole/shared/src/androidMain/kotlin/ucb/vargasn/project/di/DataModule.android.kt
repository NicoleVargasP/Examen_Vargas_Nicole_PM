package ucb.vargasn.project.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ucb.vargasn.project.AppDatabase
import ucb.vargasn.project.TodoDao
import ucb.vargasn.project.getRoomDatabase
import ucb.vargasn.project.provideDatabaseBuilder

actual val databaseModule: Module = module {
    single<AppDatabase> {
        getRoomDatabase(provideDatabaseBuilder(androidContext()))
    }
    single<TodoDao> {
        get<AppDatabase>().getDao()
    }
}
