package rex.phung.bookinfomvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rex.phung.bookinfomvvm.model.Movie
import rex.phung.bookinfomvvm.repository.MovieRepository

class MainActivityViewModel: ViewModel() {
    private val repository:MovieRepository = MovieRepository()
    val movies: MutableLiveData<List<Movie>> = repository.movies
    var nextPage: Int = 1
    var isLoading: MutableLiveData<Boolean> = repository.isLoading

    fun search(textSearch:String){
        repository.search(textSearch, 1)
        nextPage = 2
        Log.d("MainActivity", "search")
    }

    fun searchNextPage(textSearch:String){
        repository.search(textSearch, nextPage++)
        Log.d("MainActivity", "search next page")

    }
}