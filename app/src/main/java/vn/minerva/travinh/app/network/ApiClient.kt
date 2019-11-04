package vn.minerva.travinh.app.network

import com.google.gson.FieldNamingPolicy
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

class ApiClient {
    companion object {
        private fun createPosWaiterService(): TravinhService {
            val retrofit: Retrofit = get()
            return retrofit.create(TravinhService::class.java)
        }


        @JvmStatic
        private var retrofit: Retrofit = get(HttpClient.get())

        @JvmStatic
        fun get(): Retrofit {
            return retrofit
        }

        @JvmStatic
        fun get(client: OkHttpClient): Retrofit {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
            return Retrofit.Builder().baseUrl(ConfigURL.getApiUrl())
                    .addConverterFactory(AppGsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
        }


        @JvmStatic
        fun getClient(): TravinhService {
            return createPosWaiterService()
        }

        @JvmStatic
        fun updateEndPointOfRetrofit() {
            retrofit = get(HttpClient.get())
        }
    }

}