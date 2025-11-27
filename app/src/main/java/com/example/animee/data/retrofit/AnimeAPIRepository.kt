import com.example.animee.data.retrofit.AnimeApi
import com.example.animee.data.retrofit.AnimeResponse
import com.example.animee.data.retrofit.AnimeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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


    private val _animeService = _retrofit.create(AnimeService::class.java)


    suspend fun getAllAnimes() : AnimeResponse? {
        return try {
            val response = _animeService.getAllAnime(1)

             if (response.isSuccessful){
                response.body()
            }else{
                null
            }
        }catch (e: Exception){
            return null
        }
    }
}