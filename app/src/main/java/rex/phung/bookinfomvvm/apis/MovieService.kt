package rex.phung.bookinfomvvm.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rex.phung.bookinfomvvm.model.APIResponse

interface MovieService {

    @GET("list_movies.json?")
    fun searchListMovies(
        @Query("query_term") queryTerm:String,
        @Query("page") pageNumber:Int,
        @Query("limit") limit:Int = 8
    ): Call<APIResponse>
}