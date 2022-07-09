package dev.yasan.kiwi.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dev.yasan.kiwi.ValidAndroidTestObjectHolder
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.domain.entity.FlightData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FlightDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testScope = TestScope()

    private lateinit var database: FlightDatabase
    private lateinit var dao: FlightDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), FlightDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.flightDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert() = testScope.runTest {
        val flight = ValidAndroidTestObjectHolder.flight
        dao.insert(flight)
        Truth.assertThat(dao.getAll()).contains(flight)
    }

    @Test
    fun update() = testScope.runTest {
        val flight = ValidAndroidTestObjectHolder.flight
        val flight2 = ValidAndroidTestObjectHolder.flight.copy(countryFrom = "somewhere")
        dao.insert(flight)
        dao.insert(flight2)
        val databaseFlights = dao.getAll()
        Truth.assertThat(databaseFlights).contains(flight2)
        Truth.assertThat(databaseFlights.size).isEqualTo(1)
    }

    @Test
    fun delete() = testScope.runTest {
        val flight = ValidAndroidTestObjectHolder.flight
        dao.insert(flight)
        dao.delete(flight)
        Truth.assertThat(dao.getAll()).isEmpty()
    }

    @Test
    fun deleteAll() = testScope.runTest {
        val flight = ValidAndroidTestObjectHolder.flight
        dao.insert(flight.copy(id = "1"))
        dao.insert(flight.copy(id = "2"))
        dao.deleteAll()
        Truth.assertThat(dao.getAll()).isEmpty()
    }

}