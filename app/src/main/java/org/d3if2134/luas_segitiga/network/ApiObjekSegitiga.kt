package org.d3if2134.luas_segitiga.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2134.luas_segitiga.model.ObjekSegitiga
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/maltafd/assessment-1-mobpro/main/"
private const val BASE_IMG_URL = "https://raw.githubusercontent.com/maltafd/assessment-1-mobpro/main/img/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface ApiObjekSegitiga {
    @GET("objek-segitiga.json")
    suspend fun getObjekSegitiga(): List<ObjekSegitiga>
}
object ObjekSegitigaApi {
    val service: ApiObjekSegitiga by lazy {
        retrofit.create(ApiObjekSegitiga::class.java)
    }
    fun getObjekSegitigaUrl(gambar: String): String {
        return "$BASE_IMG_URL$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }