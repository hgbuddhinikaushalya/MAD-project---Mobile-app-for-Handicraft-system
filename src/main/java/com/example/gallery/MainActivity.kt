package com.example.gallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mProgress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Show the splash screen
        // Show the splash screen
        setContentView(R.layout.activity_main)
        mProgress = findViewById<View>(R.id.splash_screen_progress_bar) as ProgressBar

        // Start lengthy operation in a background thread

        // Start lengthy operation in a background thread
        Thread {
            doWork()
            startApp()
            finish()
        }.start()
    }

    private fun doWork() {
        var progress = 0
        while (progress < 100) {
            try {
                Thread.sleep(1000)
                mProgress!!.progress = progress
            } catch (e: Exception) {
                e.printStackTrace()
            }
            progress += 10
        }
    }

    private fun startApp() {
        val intent = Intent(this@MainActivity, Login::class.java)
        startActivity(intent)
    }
}