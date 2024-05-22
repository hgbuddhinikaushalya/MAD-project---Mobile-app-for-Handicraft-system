package com.example.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Payment_done : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_done)
    }

    fun payment_done(view: View) {
        val intent = Intent(this@Payment_done, Dashboard::class.java)
        startActivity(intent)
    }
}