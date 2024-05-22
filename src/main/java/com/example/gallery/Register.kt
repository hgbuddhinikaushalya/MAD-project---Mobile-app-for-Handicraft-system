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

class Register : AppCompatActivity() {

    // Get a reference to the Firebase Realtime Database
    private val database = FirebaseDatabase.getInstance().reference

    private lateinit var txtfullname: EditText
    private lateinit var txtTel: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPostal: EditText
    private lateinit var txtAddress: EditText
    private lateinit var txtPass: EditText
    private lateinit var txtRePass: EditText

    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Get references to the EditText views and the button
        txtfullname = findViewById<EditText>(R.id.txtFullName)
        txtTel = findViewById<EditText>(R.id.txtTel)
        txtEmail = findViewById<EditText>(R.id.txtEmail)
        txtPostal = findViewById<EditText>(R.id.txtPostal)
        txtAddress = findViewById<EditText>(R.id.txtAddress)
        txtPass = findViewById<EditText>(R.id.txtPass)
        txtRePass = findViewById<EditText>(R.id.txtRePass)
        btnSave = findViewById<Button>(R.id.button_reg)

        // Set a click listener on the button that will save the data to Firebase when clicked
        btnSave.setOnClickListener {
            // Save the data to Firebase
            val database = FirebaseDatabase.getInstance().reference


            // Get the values from the EditText and Spinner views
            val fullName = txtfullname.text.toString()
            val tel = txtTel.text.toString()
            val email = txtEmail.text.toString()
            val postal = txtPostal.text.toString()
            val address = txtAddress.text.toString()
            val password = txtPass.text.toString()
            val repass = txtRePass.text.toString()

            // Validate the values
            if (tel.isEmpty() || tel.length < 10) {
                // Telephone number is empty or too short
                // Show an error message
                txtTel.error = "Please enter a valid telephone number"
                return@setOnClickListener
            }

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // Email is empty or invalid
                // Show an error message
                txtEmail.error = "Please enter a valid email address"
                return@setOnClickListener
            }

            if (postal.isEmpty() || postal.length < 5) {
                // Postal code is empty or too short
                // Show an error message
                txtPostal.error = "Please enter a valid postal code"
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                // Address is empty
                // Show an error message
                txtAddress.error = "Please enter an address"
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                // Password is empty or too short
                // Show an error message
                txtPass.error = "Please enter a valid password (at least 8 characters)"
                return@setOnClickListener
            }

            if (repass.isEmpty() || repass != password) {
                // Re-entered password is empty or does not match the password
                // Show an error message
                txtRePass.error = "Please re-enter the password"
                return@setOnClickListener
            }

            val userId = database.child("user").child(email.replace(".", ",")).key
            // Create a new reservation object
            val user = User(userId.toString(), fullName, tel, email, postal , address , password)

            if (userId != null) {
                database.child("user").child(userId).setValue(user)
            }

            // Show a success message
            Toast.makeText(this, "User Registration is successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
        }
    }

    data class User(
        val userId: String = "",
        val fullName: String = "",
        val tel: String = "",
        val email: String = "",
        val postal: String = "",
        val address: String = "",
        var password: String?
    ) {
        constructor() : this("", "", "", "", "", "", "")
    }



    fun login_action(view: View) {
        val intent = Intent(this@Register, Login::class.java)
        startActivity(intent)
    }
}