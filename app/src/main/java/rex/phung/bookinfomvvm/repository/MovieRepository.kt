package rex.phung.bookinfomvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rex.phung.bookinfomvvm.apis.MovieService
import rex.phung.bookinfomvvm.model.APIResponse
import rex.phung.bookinfomvvm.model.Movie

class MovieRepository {
    private val API_Base:String = "https://yts.mx/api/v2/"
    val movies:MutableLiveData<List<Movie>> = MutableLiveData()
    var moviesStatic = mutableListOf<Movie>()
     var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        movies.value = moviesStatic
        isLoading.value = false
    }

//    fun search(textSearch:String){
//        val retrofit = Retrofit.Builder()
//                .baseUrl(API_Base)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        val service = retrofit.create(MovieService::class.java)
//        val call = service.searchListMovies(textSearch)
//
//        call.enqueue(object :Callback<APIResponse>{
//            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
//                if (response.code() == 200){
//                    val APIResponse = response.body()!!
//                    if(APIResponse.data?.movies!=null){
//                        moviesStatic.clear()
//                        moviesStatic.addAll(APIResponse.data!!.movies!!.toMutableList())
//                        movies.postValue(moviesStatic)
//                        Log.d("adapter", movies.value?.size.toString())
//                    }
//
//                }
//            }
//
//            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
//                    Log.d("MovieRepository", t.message.toString())
//            }
//
//        })
//    }
    fun search(textSearch:String, pageNumber: Int){
        isLoading.value = true

        val retrofit = Retrofit.Builder()
            .baseUrl(API_Base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val call = service.searchListMovies(textSearch, pageNumber = pageNumber)

        call.enqueue(object :Callback<APIResponse>{
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                isLoading.value = false
                if (response.code() == 200){
                    val APIResponse = response.body()!!
                    if(APIResponse.data?.movies!=null){

                        if(pageNumber == 1) moviesStatic.clear()

                        moviesStatic.addAll(APIResponse.data!!.movies!!.toMutableList())
                        movies.postValue(moviesStatic)
                        Log.d("MainActivity","New Length" + movies.value?.size.toString())
                    }

                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                Log.d("MovieRepository", t.message.toString())
                isLoading.value = false
            }

        })
    }

}