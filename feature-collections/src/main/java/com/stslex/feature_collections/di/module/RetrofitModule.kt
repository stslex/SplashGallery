package com.stslex.feature_collections.di.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.stslex.feature_collections.di.scopes.OfflineInterceptor
import com.stslex.feature_collections.di.scopes.OnlineInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesRetrofitClient(
        mLoggingInterceptor: HttpLoggingInterceptor,
        @OnlineInterceptor onlineInterceptor: Interceptor,
        @OfflineInterceptor offlineInterceptor: Interceptor,
        context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(mLoggingInterceptor)
        .addInterceptor(if (checkNetwork(context)) onlineInterceptor else offlineInterceptor)
        .cache(Cache(context.cacheDir, MAX_CACHE_SIZE))
        .build()

    private fun checkNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @OnlineInterceptor
    fun providesOnlineInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$MAX_AGE")
            .removeHeader("Pragma")
            .build()
    }

    @Provides
    @OfflineInterceptor
    fun providesOfflineInterceptor(): Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        request = request.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=$MAX_STALE")
            .removeHeader("Pragma")
            .build()
        chain.proceed(request)
    }

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"
        private const val MAX_STALE = 60 * 60 * 12
        private const val MAX_AGE = 60 * 60 * 3
        private const val MAX_CACHE_SIZE = 10 * 1024 * 1024 * 8L
    }
}