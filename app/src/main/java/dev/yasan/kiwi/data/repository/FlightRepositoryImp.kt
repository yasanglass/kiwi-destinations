package dev.yasan.kiwi.data.repository

import android.content.SharedPreferences
import android.text.format.DateUtils
import android.util.Log
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.R
import dev.yasan.kiwi.data.entity.FlightSearchResultResponse
import dev.yasan.kiwi.data.source.local.FlightDao
import dev.yasan.kiwi.data.source.remote.KiwiApi
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.domain.repository.FlightRepository
import java.io.IOException
import javax.inject.Inject

class FlightRepositoryImp @Inject constructor(
    private val kiwiApi: KiwiApi,
    private val flightDao: FlightDao,
    private val sp: SharedPreferences
) : FlightRepository {

    companion object {
        private const val TAG = "FlightRepositoryImp"

        private const val FLIGHTS_UPDATE_TIME_MILLIS = "flights_update_time"
    }

    override suspend fun searchRemoteFlights(
        sort: String,
        ascending: Int,
        flyFrom: String,
        flyTo: String,
        limit: Int,
    ): Resource<FlightSearchResultResponse> {

        return try {
            val response = kiwiApi.searchFlights(
                sort = sort,
                ascending = ascending,
                flyFrom = flyFrom,
                flyTo = flyTo,
                limit = limit,
            )

            if (response.isSuccessful) {
                val data = response.body()!!
                val validFlights = data.flightResponses.filter { it.isValid() }
                Resource.Success(data = data.copy(flightResponses = validFlights))
            } else {
                Resource.Error(messageResourceId = R.string.error_unsuccessful)
            }

        } catch (e: IOException) {
            Log.d(TAG, "searchFlights: ${e.message}")
            Resource.Error(messageResourceId = R.string.error_network)
        } catch (e: Exception) {
            Log.d(TAG, "searchFlights: ${e.message}")
            Resource.Error(messageResourceId = R.string.error_generic)
        }

    }

    override suspend fun getLocalFlights(): List<Flight> {
        return flightDao.getAll()
    }

    override suspend fun updateLocalFlights(newFlights: List<Flight>) {
        flightDao.deleteAll()
        newFlights.forEach { flight ->
            flightDao.insert(flight = flight)
        }
        sp.edit().putLong(FLIGHTS_UPDATE_TIME_MILLIS, System.currentTimeMillis()).apply()
    }

    override suspend fun shouldFetchFreshData(): Boolean {
        val updateTimeMillis = sp.getLong(FLIGHTS_UPDATE_TIME_MILLIS, System.currentTimeMillis())

        val isUpdatedToday = DateUtils.isToday(updateTimeMillis)
        val noLocalFlightsAvailable = getLocalFlights().isEmpty()

        val shouldFetchFreshData = !isUpdatedToday || noLocalFlightsAvailable

        Log.d(TAG, "shouldFetchFreshData: $shouldFetchFreshData (!$isUpdatedToday || $noLocalFlightsAvailable)")
        return shouldFetchFreshData
    }

}