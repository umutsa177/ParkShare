package com.umutsayar.parkshare.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.maps.android.ktx.BuildConfig
import com.umutsayar.parkshare.data.local.TokenManager
import com.umutsayar.parkshare.data.remote.api.auth.AuthService
import com.umutsayar.parkshare.data.remote.api.parking.ParkingService
import com.umutsayar.parkshare.data.remote.api.reservation.ReservationService
import com.umutsayar.parkshare.data.remote.api.review.ReviewService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// DataStore extension
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "parkshare_prefs")

val BASE_URL = "http://10.0.2.2:5000/api/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideTokenManager(
        dataStore: DataStore<Preferences>
    ): TokenManager {
        return TokenManager(dataStore)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenManager: TokenManager
    ): Interceptor {
        return Interceptor { chain ->
            val token = tokenManager.getTokenSync()
            val request = if (token != null) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                chain.request()
            }
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideParkingService(
        retrofit: Retrofit
    ): ParkingService {
        return retrofit.create(ParkingService::class.java)
    }

    @Provides
    @Singleton
    fun provideReservationService(
        retrofit: Retrofit
    ): ReservationService {
        return retrofit.create(ReservationService::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewService(
        retrofit: Retrofit
    ): ReviewService {
        return retrofit.create(ReviewService::class.java)
    }
}