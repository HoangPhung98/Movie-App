package rex.phung.bookinfomvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rex.phung.bookinfomvvm.adapter.MovieAdapter
import rex.phung.bookinfomvvm.databinding.ActivityMainBinding
import rex.phung.bookinfomvvm.model.Movie
import rex.phung.bookinfomvvm.viewmodel.MainActivityViewModel
import rex.phung.bookinfomvvm.viewmodel.MainActivityViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
    lateinit var adapter: MovieAdapter
    lateinit var recyclerView:RecyclerView

    lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //view
        binding.btSearch.setOnClickListener(this)

        //viewModel
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory()).get(MainActivityViewModel::class.java)

        //recycler
        recyclerView = binding.recyclerMovies
        recyclerView.layoutManager = layoutManager
        adapter = MovieAdapter(viewModel.movies.value!!)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(layoutManager.findLastVisibleItemPosition() == viewModel.movies.value?.size?.minus(1)
                    && viewModel.isLoading.value == false
                ){
                    viewModel.searchNextPage(binding.etSearch.text.toString())
                    Log.d("MainActivity", "Scroll to the end")
                }
                Log.d("MainActivity", layoutManager.findLastVisibleItemPosition().toString())

            }
        })

        viewModel.movies.observe(this, Observer {
            Log.d("MainActivity", viewModel.movies.value?.size.toString())
            adapter.notifyDataSetChanged()
//            binding.etSearch.text.clear()
        })
        viewModel.isLoading.observe(this,{
            if (viewModel.isLoading.value == true)
                binding.progressBarSearchMovieLoading.visibility = View.VISIBLE
            else{
                binding.progressBarSearchMovieLoading.visibility = View.GONE
            }
        })
    }

    override fun onClick(v: View?) {
        if (binding.etSearch.text.isNotEmpty())
            viewModel.search(binding.etSearch.text.toString())
    }
}