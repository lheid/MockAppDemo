package com.crane.mockappdemo.main

import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.crane.mockapp.annotations.MockAppLayout
import com.crane.mockapp.annotations.MockAppView
import com.crane.mockapp.core.MockAppActivity
import com.crane.mockapp.core.PaddingItemDecoration
import com.google.android.material.appbar.AppBarLayout

@MockAppLayout(projectName = "mockappdemo", layoutName = "main")
class MainActivity : MockAppActivity() {

    @MockAppView
    private lateinit var recyclerView: RecyclerView

    @MockAppView
    private lateinit var appBarLayout: AppBarLayout

    private lateinit var mainViewModel: MainViewModel

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.samples.observe(this, Observer { samples ->
            updateSamples(samples)
        })

        mainAdapter = MainAdapter(this)
        recyclerView.adapter = mainAdapter

        mainViewModel.onLoadSamples()
    }

    fun updateSamples(samples: List<MainItem>) {
        mainAdapter.update(samples)
    }
}