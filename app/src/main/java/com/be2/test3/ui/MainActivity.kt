package com.be2.test3.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.be2.test3.models.Movie
import com.be2.test3.R
import com.be2.test3.databinding.ActivityMainBinding
import com.be2.test3.models.DataViewModel
import com.be2.test3.models.DataViewModelFactory
import com.be2.test3.network.DataApi
import com.be2.test3.repositories.DataRepository
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() , RecyclerViewClickListener{
    private lateinit var factory: DataViewModelFactory

    private val sharedPrefFile = "kotlinsharedpreference"
    internal var activityMainBinding: ActivityMainBinding?= null
    private lateinit var viewModel: DataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = DataApi()
        val repository = DataRepository(api)

        factory = DataViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(DataViewModel::class.java)
        viewModel.getMovies()

        viewModel.movies.observe(this, Observer { movies ->
            viewdeveloper.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = DataAdapter(movies, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, movie: Movie) {
        when(view.id){
            R.id.imageView -> {
                Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
                val name:String = movie.title
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                        Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor =  sharedPreferences.edit()
                editor.putString("title",name)
                editor.putString("url",movie.url)
                editor.apply()
                editor.commit()
                    //intent code
                val intent = Intent(this, ReceiveActivity::class.java)
                startActivity(intent)
            }

        }
    }
}