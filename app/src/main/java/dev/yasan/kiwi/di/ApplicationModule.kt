package dev.yasan.kiwi.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kiwi.data.repository.FlightRepositoryImp
import dev.yasan.kiwi.data.source.local.FlightDao
import dev.yasan.kiwi.data.source.local.FlightDatabase
import dev.yasan.kiwi.BuildConfig
import dev.yasan.kiwi.data.source.remote.KiwiApi
import dev.yasan.kiwi.domain.repository.FlightRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideKiwiApi(retrofit: Retrofit): KiwiApi =
        retrofit.create(KiwiApi::class.java)

    @Singleton
    @Provides
    fun provideFlightRepository(
        kiwiApi: KiwiApi,
        flightDao: FlightDao,
        sp: SharedPreferences
    ): FlightRepository =
        FlightRepositoryImp(kiwiApi = kiwiApi, flightDao = flightDao, sp = sp)

    @Singleton
    @Provides
    fun provideFlightDatabase(
        app: Application,
        callback: FlightDatabase.CallBack
    ) = Room.databaseBuilder(app, FlightDatabase::class.java, "flight_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideFlightDao(flightDatabase: FlightDatabase) = flightDatabase.flightDao()

}