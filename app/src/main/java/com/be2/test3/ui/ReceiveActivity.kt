package com.be2.test3.ui

import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Point
import android.media.MediaPlayer.OnPreparedListener
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.widget.MediaController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.be2.test3.R
import com.be2.test3.models.ReceiveViewModel
import com.be2.test3.databinding.ActivityReceiveBinding
import kotlinx.android.synthetic.main.activity_receive.*


class ReceiveActivity : AppCompatActivity() , listner {

    lateinit var sharedPreferences: SharedPreferences
    private val sharedPrefFile = "kotlinsharedpreference"
    var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        val binding: ActivityReceiveBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_receive
        )
        val viewModel = ViewModelProviders.of(this).get(ReceiveViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.listner = this
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val sharedNameValue = sharedPreferences.getString("title", "")
        val sharedUrlValue = sharedPreferences.getString("url", "")

        url = sharedUrlValue
        textView.setText(sharedNameValue).toString()
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        textView.setText(sharedNameValue).toString()
        videoView.setVideoPath(sharedUrlValue)
        videoView.setOnPreparedListener(OnPreparedListener { mp ->
            mp.setOnVideoSizeChangedListener { mp, width, height ->
                val  mc = MediaController(this)
                videoView.setMediaController(mc)
                mc.setAnchorView(videoView)
            }
        })
        videoView.start()
    }

    override fun onfull() {
//        val sharedUrlValue = sharedPreferences.getString("url", "")
        val intent = Intent(this, FullscreenActivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onpip() {
            val pip = PictureInPictureParams.Builder()
            val display = windowManager?.defaultDisplay
            val point = Point()
            display?.getRealSize(point)
            pip.setAspectRatio(Rational(point.x, point.y))
            enterPictureInPictureMode(pip.build())
    }
}