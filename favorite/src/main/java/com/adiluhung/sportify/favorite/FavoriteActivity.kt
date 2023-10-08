package com.adiluhung.sportify.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adiluhung.sportify.R
import com.adiluhung.sportify.core.ui.TeamAdapter
import com.adiluhung.sportify.detail.DetailTeamActivity
import com.adiluhung.sportify.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorite Team"

        // action bar back button handle
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val teamAdapter = TeamAdapter()

        teamAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailTeamActivity::class.java)
            intent.putExtra(DetailTeamActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteTeam.observe(this) { team ->
            teamAdapter.setData(team)
        }

        with(binding.rvTeam) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = teamAdapter
        }
    }
}