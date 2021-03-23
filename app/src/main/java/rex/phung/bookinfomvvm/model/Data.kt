package rex.phung.bookinfomvvm.model

import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("movie_count")
    var movieCount:Int = 0

    @SerializedName("limit")
    var limit: Int= 20


    @SerializedName("page_number")
    var pageNumber:Int = 1

    @SerializedName("movies")
    var movies: List<Movie>? = null
}