package dev.yasan.kiwi.data.source.local

import androidx.room.*
import dev.yasan.kiwi.domain.entity.Flight

/**
 * [Flight] data access object (DAO). Used to read & write [Flight]s.
 */
@Dao
interface FlightDao {

    @Query("SELECT * FROM flights")
    suspend fun getAll(): List<Flight>

    @Query("SELECT * FROM flights WHERE id=:id")
    suspend fun getById(id: String): Flight?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flight: Flight)

    @Delete
    suspend fun delete(flight: Flight)

    @Query("DELETE FROM flights")
    suspend fun deleteAll()

}
