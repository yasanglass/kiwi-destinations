package dev.yasan.kiwi.data.repository

import android.util.Log
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.R
import dev.yasan.kiwi.data.entity.SearchResultResponse
import dev.yasan.kiwi.data.source.remote.KiwiApi
import dev.yasan.kiwi.domain.repository.FlightRepository
import java.io.IOException
import javax.inject.Inject

class FlightRepositoryImp @Inject constructor(
    private val kiwiApi: KiwiApi
) : FlightRepository {

    companion object {
        private const val TAG = "FlightRepositoryImp"
    }

    override suspend fun searchFlights(
        sort: String,
        ascending: Int,
        flyFrom: String,
        flyTo: String,
        limit: Int,
    ): Resource<SearchResultResponse> {

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

}