package rex.phung.bookinfomvvm.model

import com.google.gson.annotations.SerializedName

class APIResponse {
    @SerializedName("status")
    var status:String="ok"

    @SerializedName("status_message")
    var statusMessage:String = "Successful"

    @SerializedName("data")
    var data:Data? = null
}