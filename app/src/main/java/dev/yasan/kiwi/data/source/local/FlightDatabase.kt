package dev.yasan.kiwi.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yasan.kiwi.domain.entity.Flight
import javax.inject.Inject

/**
 * Room database abstract class for the database.
 *
 * @see Flight
 * @see RoomDatabase
 */
@Database(entities = [Flight::class], version = 1)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun flightDao(): FlightDao

    class CallBack @Inject constructor() : RoomDatabase.Callback()
}
