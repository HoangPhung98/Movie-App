package rex.phung.bookinfomvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rex.phung.bookinfomvvm.R
import rex.phung.bookinfomvvm.model.Movie

class MovieAdapter(val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.VHBook>() {

    class VHBook(view: View, val parent: ViewGroup): RecyclerView.ViewHolder(view){

        var tvTitle:TextView
        var tvSummary: TextView
        var tvTime: TextView
        var ivPoster: ImageView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            tvSummary = view.findViewById(R.id.tvSummary)
            tvTime = view.findViewById(R.id.tvTime)
            ivPoster = view.findViewById(R.id.ivPoster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHBook {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return VHBook(view, parent)

    }

    override fun onBindViewHolder(holder: VHBook, position: Int) {
        val data = movies[position]
        Log.d("adapter", data.title)
        holder.tvTitle.text = data.title
        holder.tvTime.text = data.runtime.toString()
        holder.tvSummary.text = data.summary
        Glide.with(holder.parent.context).load(data.mediumCoverImage).into(holder.ivPoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}