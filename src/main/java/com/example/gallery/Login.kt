package com.example.gallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    // Get a reference to the Firebase Realtime Database
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)






    }

    fun reg_action(view: View) {
        val intent = Intent(this@Login, Register::class.java)
        startActivity(intent)
    }

    fun reset_password_action(view: View) {
        val intent = Intent(this@Login, Password_reset::class.java)
        startActivity(intent)
    }

    fun login_action(view: View) {
        val intent = Intent(this@Login, Dashboard::class.java)
        startActivity(intent)
    }


}