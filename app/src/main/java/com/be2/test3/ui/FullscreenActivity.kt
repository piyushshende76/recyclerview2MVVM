package com.be2.test3.ui

import androidx.appcompat.app.AppCompatActivity
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.MediaController
import com.be2.test3.R
import kotlinx.android.synthetic.main.activity_fullscreen.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
           val url=intent.getStringExtra("url")

           requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
           val metrics = DisplayMetrics()
           getWindowManager()?.getDefaultDisplay()?.getMetrics(metrics)
           val params = videoView2.layoutParams as LinearLayout.LayoutParams
           params.width = metrics.widthPixels
           params.height = metrics.heightPixels
           params.leftMargin = 0
           videoView2.layoutParams = params
           videoView2.setVideoPath(url)
           videoView2.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
               mp.setOnVideoSizeChangedListener { mp, width, height ->
                   val mc = MediaController(this)
                   videoView2.setMediaController(mc)
                   mc.setAnchorView(videoView2)
               }
           })
           videoView2.start()

    }



}