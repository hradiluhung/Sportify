package com.adiluhung.sportify.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.adiluhung.sportify.R
import com.adiluhung.sportify.core.domain.model.Team
import com.adiluhung.sportify.databinding.ActivityDetailTeamBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTeamActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailTeamViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailTeam = intent.getParcelableExtra<Team>(EXTRA_DATA)

        binding.tvTeamName.text = detailTeam?.strTeam
        binding.tvDescription.text = detailTeam?.strDescriptionEN
        Glide.with(this@DetailTeamActivity)
            .load(detailTeam?.strTeamBadge)
            .into(binding.ivTeamBadge)
        Glide.with(this@DetailTeamActivity)
            .load(detailTeam?.strStadiumThumb)
            .into(binding.ivStadium)


        val unwrappedDrawable = AppCompatResources.getDrawable(this, R.drawable.circle_shape)
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(detailTeam?.strKitColour1))
        binding.ivKitColor.background = wrappedDrawable

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_team)

        // action bar back button handle
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.fabTeamPlayer.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_player
            )
        )

        var statusFavorite = detailTeam?.isFavorite
        if (statusFavorite != null) {
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite!!
                if (detailTeam != null) {
                    detailTeamViewModel.setFavoriteTeam(detailTeam, statusFavorite!!)
                }
                setStatusFavorite(statusFavorite!!)
            }
        }

        binding.fabTeamPlayer.setOnClickListener {
            if (detailTeam != null) {
                val uri = Uri.parse("sportify://player")
                // send team name to player module
                val intent = Intent(Intent.ACTION_VIEW, uri)
                    .putExtra("teamName", detailTeam.strTeam)
                startActivity(intent)
            }
        }

    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}