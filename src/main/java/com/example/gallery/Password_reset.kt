package com.example.gallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Password_reset : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPass: EditText
    private lateinit var txtRePass: EditText
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        txtEmail = findViewById<EditText>(R.id.txtEmail)
        txtPass = findViewById<EditText>(R.id.txtPass)
        txtRePass = findViewById<EditText>(R.id.txtRePass)
        buttonReset = findViewById<Button>(R.id.button_reset)

        // Set a click listener on the button that will save the data to Firebase when clicked
        buttonReset.setOnClickListener {

            val email = txtEmail.text.toString()
            val pass = txtPass.text.toString()
            val repass = txtRePass.text.toString()

            if (pass != repass) {
                // New password and confirm password don't match, show an error message
                txtRePass.error = "Passwords don't match"
                return@setOnClickListener
            }

            getUserByEmail(email , pass)




        }
    }

    private fun getUserByEmail(email: String , pass: String) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("user")

        val emailKey = email.replace(".", ",")
        val emailRef = userRef.child(emailKey)

        emailRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    // Data found
                    updatePasswordByEmail(email , pass)
                    Toast.makeText(this@Password_reset, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Password_reset, Login::class.java)
                    startActivity(intent)

                } else {
                    // Data not found, show error message
                    Toast.makeText(this@Password_reset, "There are no email", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(@NonNull error: DatabaseError) {

                Log.d("getUserData", "Database error: " + error.message)
            }
        })
    }

    private fun updatePasswordByEmail(email: String, newPassword: String) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("user")
        val emailKey = email.replace(".", ",")
        val emailRef = userRef.child(emailKey)

        emailRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(Register.User::class.java)
                    if (user != null) {
                        user.password = newPassword
                        emailRef.setValue(user)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                }
                            }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("updateUserPassword", "Database error: " + error.message)
            }
        })
    }

    fun login_action(view: View) {
        val intent = Intent(this@Password_reset, Login::class.java)
        startActivity(intent)
    }
}