package com.baish.skyscanner.ui.main.video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.view.View
import com.baish.skyscanner.R
import com.baish.skyscanner.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityVideoBinding
    private lateinit var exoMediaPlayer: ExoPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var stateBuilder: PlaybackStateCompat.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initExoPlayer()
        getVideos()



       // play("http://images-assets.nasa.gov/video/Laser Geodynamics Satellite (LAGEOS)/Laser Geodynamics Satellite (LAGEOS)~mobile.mp4")


        //http://images-assets.nasa.gov/video/NHQ_2019_0508_We Are NASA/NHQ_2019_0508_We Are NASA~orig.mp4
        //http://images-assets.nasa.gov/video/NHQ_2017_0523_FY18 State Of NASA Budget/NHQ_2017_0523_FY18 State Of NASA Budget~medium.mp4
        //http://images-assets.nasa.gov/video/NASA_2020_0327_The Impact of Coronavirus to NASA’s Missions on This Week @NASA – March 27, 2020/NASA_2020_0327_The Impact of Coronavirus to NASA’s Missions on This Week @NASA – March 27, 2020~large.mp4
        //http://images-assets.nasa.gov/video/NHQ_2017_0523_Acting Administrator Robert Lightfoot Discusses NASAs FY2018 NASA Budget Request/NHQ_2017_0523_Acting Administrator Robert Lightfoot Discusses NASAs FY2018 NASA Budget Request~medium.mp4
    }

    private fun getVideos() {
        val url = intent.getStringExtra("video1")
        url?.let { play(it) }

        val urlTwo = intent.getStringExtra("video2")
        urlTwo?.let { play(it) }

        val urlThree = intent.getStringExtra("video3")
        urlThree?.let { play(it) }

        val urlFour = intent.getStringExtra("video4")
        urlFour?.let { play(it) }
    }

    private fun initExoPlayer() {
        binding.player.player = initialize()
    }

    private fun initialize(): ExoPlayer {
        initializePlayer()
        initializeMediaSession()
        return exoMediaPlayer
    }

    private fun initializePlayer() {
        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val renderersFactory = DefaultRenderersFactory(applicationContext)
        exoMediaPlayer =
            ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl)
    }

    private fun initializeMediaSession() {
        mediaSession = MediaSessionCompat(applicationContext, "sadasdasd")
        mediaSession.setFlags(
            MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                    MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        )
        mediaSession.setMediaButtonReceiver(null)

        stateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_PLAY_PAUSE or
                        PlaybackStateCompat.ACTION_FAST_FORWARD or
                        PlaybackStateCompat.ACTION_REWIND
            )

        mediaSession.setPlaybackState(stateBuilder.build())

        mediaSession.setCallback(SessionCallback())

        mediaSession.isActive = true
    }

    private inner class SessionCallback : MediaSessionCompat.Callback() {

        private val SEEK_WINDOW_MILLIS = 1_000

        override fun onPlay() {
            exoMediaPlayer.playWhenReady = true
        }

        override fun onPause() {
            exoMediaPlayer.playWhenReady = false
        }

        override fun onRewind() {
            exoMediaPlayer.seekTo(exoMediaPlayer.currentPosition - SEEK_WINDOW_MILLIS)
        }

        override fun onFastForward() {
            exoMediaPlayer.seekTo(exoMediaPlayer.currentPosition + SEEK_WINDOW_MILLIS)
        }
    }

    val listener = object: Player.EventListener{
        override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

        }

        override fun onTracksChanged(
            trackGroups: TrackGroupArray?,
            trackSelections: TrackSelectionArray?
        ) {
        }

        override fun onLoadingChanged(isLoading: Boolean) {
            if (isLoading){
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
        }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
        }

        override fun onPlayerError(error: ExoPlaybackException?) {
        }

        override fun onPositionDiscontinuity(reason: Int) {
        }

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
        }

        override fun onSeekProcessed() {
        }

    }

    private fun play(url: String) {

        val userAgent = Util.getUserAgent(
            applicationContext,
            applicationContext.getString(R.string.app_name)
        )

        val httpDataSourceFactory = DefaultHttpDataSourceFactory(
            userAgent,
            null /* listener */,
            DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
            DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
            true /* allowCrossProtocolRedirects */
        )

        val mediaSource = ExtractorMediaSource.Factory(
            DefaultDataSourceFactory(
                applicationContext,
                null,
                httpDataSourceFactory
            )
        )
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(url))

        exoMediaPlayer.prepare(mediaSource)

        exoMediaPlayer.playWhenReady = true

        exoMediaPlayer.addListener(listener)

    }
}