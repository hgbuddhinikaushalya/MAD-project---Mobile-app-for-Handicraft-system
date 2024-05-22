package com.example.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Add_item : AppCompatActivity() {

    private lateinit var txtItemName: EditText
    private lateinit var txtPrice: EditText
    private lateinit var txtImage: EditText
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        // Get references to the EditText views and the button
        txtItemName = findViewById<EditText>(R.id.txtItemName)
        txtPrice = findViewById<EditText>(R.id.txtPrice)
        txtImage = findViewById<EditText>(R.id.txtImg)

        btnSave = findViewById<Button>(R.id.addBtn)

        // Set a click listener on the button that will save the data to Firebase when clicked
        btnSave.setOnClickListener {

            // Save the data to Firebase
            val database = FirebaseDatabase.getInstance().reference

            // Get the values from the EditText and Spinner views
            val itemname = txtItemName.text.toString()
            val price = txtPrice.text.toString()
            val imagename = txtImage.text.toString()
            var itemcode = generateRandomCode(10)

            val itemId = database.child("item").child(itemcode).key
            // Create a new reservation object
            val item = Item(itemId.toString(), itemname, price, imagename)

            if (itemId != null) {
                database.child("item").child(itemId).setValue(item)
            }

            // Show a success message
            Toast.makeText(this, "Item Adding successful!", Toast.LENGTH_SHORT).show()
//          val intent = Intent(this@Add_item, Login::class.java)
//          startActivity(intent)
        }
    }

    data class Item(
        val itemId: String = "",
        val itemname: String = "",
        val price: String = "",
        val imagename: String?
    ) {
        constructor() : this("", "", "", "")
    }

    fun generateRandomCode(length: Int): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val random = java.util.Random()
        val sb = StringBuilder(length)
        for (i in 0 until length) {
            sb.append(chars[random.nextInt(chars.length)])
        }
        return sb.toString()
    }

    fun back(view: View) {
        val intent = Intent(this@Add_item, Dashboard::class.java)
        startActivity(intent)
    }

}