package com.example.offlineandroidapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.offlineandroidapp.R
import com.example.offlineandroidapp.data.local.UserDatabase
import com.example.offlineandroidapp.data.remote.ApiService
import com.example.offlineandroidapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupRecyclerView()
        setupSearchView()
        observeData()
    }

    private fun setupViewModel() {
        val database = UserDatabase.getDatabase(applicationContext)
        val apiService = ApiService.create()
        val repository = UserRepository(database.userDao(), apiService)
        val factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        adapter = UserAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupSearchView() {
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchQueryChanged(newText ?: "")
                return true
            }
        })
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.users.collect { users ->
                adapter.submitList(users)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.errorMessage.collect { error ->
                error?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    viewModel.clearError()
                }
            }
        }
    }
}