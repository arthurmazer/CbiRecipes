package com.mazer.cbirecipes.presentation.ui.modules.recipe

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_READY
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.mazer.cbirecipes.databinding.ActivityRecipeBinding
import com.mazer.cbirecipes.domain.entities.Recipe
import com.mazer.cbirecipes.presentation.ui.adapters.home.IngredientsAdapter
import com.mazer.cbirecipes.presentation.ui.adapters.home.StepsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeActivity : AppCompatActivity() {

    private val viewModel : RecipeViewModel by viewModel()
    private lateinit var ingredientsAdapter: IngredientsAdapter
    private lateinit var stepsAdapter: StepsAdapter
    private lateinit var binding: ActivityRecipeBinding

    private var player: ExoPlayer? = null
    private val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        registerObservers()
        intent.extras?.let {
            viewModel.setExtras(it)
        }
    }

    override fun onPause() {
        super.onPause()
        pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun setupView() {
        setupIngredientsAdapter()
        setupStepsAdapter()
    }

    private fun setupStepsAdapter() {
        ingredientsAdapter = IngredientsAdapter()
        binding.rvIngredients.adapter = ingredientsAdapter
        binding.rvIngredients.isNestedScrollingEnabled = false
    }

    private fun setupIngredientsAdapter() {
        stepsAdapter = StepsAdapter {
            viewModel.getVideoUrl(it)
        }
        binding.rvSteps.adapter = stepsAdapter
        binding.rvSteps.isNestedScrollingEnabled = false
    }

    private fun registerObservers() {
        viewModel.recipe.observe(this) {
            setRecipeDetails(it)
        }
        viewModel.teaserUrl.observe(this) {
            initPlayer(it)
        }
    }

    private fun setRecipeDetails(recipe: Recipe){
        binding.tvRecipeTitle.text = recipe.name
        binding.tvRecipeDescription.text = recipe.description
        ingredientsAdapter.setList(recipe.ingredients)
        stepsAdapter.setList(recipe.steps)
        binding.tvServings.text = recipe.servings

    }


    @SuppressLint("UnsafeOptInUsageError")
    private fun  initPlayer(urlVideo: String){
        releasePlayer()
        player = ExoPlayer.Builder(this)
            .build()
            .apply {
                setMediaSource(getProgressiveMediaSource(urlVideo))
                prepare()
                addListener(playerListener)
            }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun getProgressiveMediaSource(urlVideo: String): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(urlVideo)))
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupControlVideo(){
        binding.playerView.setOnTouchListener { _, _ ->
            binding.playerView.useController = true
            true
        }
    }

    private fun releasePlayer(){
        player?.apply {
            playWhenReady = false
            release()
        }
        player = null
    }

    private fun pause(){
        player?.playWhenReady = false
    }

    private fun play(){
        player?.playWhenReady = true
    }

    private fun restartPlayer(){
        player?.seekTo(0)
        player?.playWhenReady = true
    }

    private val playerListener = object: Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when(playbackState){
                STATE_ENDED -> restartPlayer()
                STATE_READY -> {
                    binding.playerView.player = player
                    play()
                }
            }
        }
    }
}