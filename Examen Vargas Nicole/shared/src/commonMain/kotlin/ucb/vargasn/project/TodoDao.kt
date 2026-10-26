package ucb.vargasn.project

import androidx.room3.Dao
import androidx.room3.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<TodoEntity>
}
