package ucb.vargasn.project

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase

fun provideDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("earthquakes.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
