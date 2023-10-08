package com.adiluhung.sportify.player

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adiluhung.sportify.R
import com.adiluhung.sportify.core.data.Resource
import com.adiluhung.sportify.core.ui.PlayerAdapter
import com.adiluhung.sportify.player.databinding.ActivityPlayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(playerModule)

        val teamName = intent.getStringExtra("teamName")
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // action bar back button handle
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.title = "Player of Team"

        if (teamName != null) {
            getPlayerData(teamName)
        }
    }

    private fun getPlayerData(teamName: String) {
        playerViewModel.player.observe(this) { player ->
            if (player != null) {
                val playerAdapter = PlayerAdapter()

                when (player) {
                    is Resource.Loading -> binding.progressBar.visibility =
                        View.VISIBLE

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        playerAdapter.setData(player.data)

                        with(binding.rvPlayer) {
                            layoutManager = LinearLayoutManager(context)
                            setHasFixedSize(true)
                            adapter = playerAdapter
                        }
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }


}