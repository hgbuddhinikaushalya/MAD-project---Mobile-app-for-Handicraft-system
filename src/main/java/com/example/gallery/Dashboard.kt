package com.example.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }


    fun action_admin(view: View) {
        val intent = Intent(this@Dashboard, Add_item::class.java)
        startActivity(intent)
    }
    fun cart_action(view: View) {

    }
    fun delivery_action(view: View) {}
    fun shopping_action(view: View) {
        val intent = Intent(this@Dashboard, Items_for_cus::class.java)
        startActivity(intent)
    }
}