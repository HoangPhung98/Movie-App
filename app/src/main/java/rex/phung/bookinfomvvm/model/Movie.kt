package rex.phung.bookinfomvvm.model

import com.google.gson.annotations.SerializedName

class Movie{

    @SerializedName("id")
    var id:Int = 0
    @SerializedName("url")
    var url:String = ""
    @SerializedName("imdb_code")
    var imdbCode:String =""
    @SerializedName("title")
    var title:String=""

//    @SerializedName("title_english")
//    @SerializedName("title_long"): "A Little Italian Vacation (2021)",
//    "slug": "a-little-italian-vacation-2021",
    @SerializedName("year")
    var year:Int = 2014
    @SerializedName("rating")
    var rating:Float = 8.0f
    @SerializedName("runtime")
    var runtime:Int = 90
//    "genres": [
//    "Comedy",
//    "Romance"
//    ],
    @SerializedName("summary")
    var summary:String = ""
    @SerializedName("language")
    var language:String = "en"
//    "mpa_rating": "",
    @SerializedName("background_image")
    var backgroundImage:String = ""
//    "background_image_original": "https://yts.mx/assets/images/movies/a_little_italian_vacation_2021/background.jpg",
    @SerializedName("small_cover_image")
    var smallCoverImage = ""
    @SerializedName("medium_cover_image")
    var mediumCoverImage = ""
//    @SerializedName("large_cover_image"): "https://yts.mx/assets/images/movies/a_little_italian_vacation_2021/large-cover.jpg",
    @SerializedName("state")
    var state:String = "ok"
}
