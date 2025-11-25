import com.example.animee.data.retrofit.AnimeService
import com.example.animee.data.retrofit.ApiAnime
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.emptyList

object AnimeAPIRepository {
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()


    private val _animeService = _retrofit.create(AnimeService::class.java);



    suspend fun getAnimesFromAPI() : List<ApiAnime> {
        return try {
            val response = _animeService.getAllAnime()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
            }
        }


}