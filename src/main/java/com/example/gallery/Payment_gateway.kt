package com.example.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Payment_gateway : AppCompatActivity() {

    // Get a reference to the Firebase Realtime Database
    private val database = FirebaseDatabase.getInstance().reference

    private lateinit var txtCardNum: EditText
    private lateinit var txtHolder: EditText
    private lateinit var txtCVV: EditText
    private lateinit var txtExpireDate: EditText

    private lateinit var btnPay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_gateway)

        // Get references to the EditText views and the button
        txtCardNum = findViewById<EditText>(R.id.txtCard)
        txtHolder = findViewById<EditText>(R.id.txtCardHolder)
        txtCVV = findViewById<EditText>(R.id.txtCVV)
        txtExpireDate = findViewById<EditText>(R.id.txtExpireDate)

        btnPay = findViewById<Button>(R.id.paymentBtn)


        // Set a click listener on the button that will save the data to Firebase when clicked
        btnPay.setOnClickListener {
            // Save the data to Firebase
            val database = FirebaseDatabase.getInstance().reference


            // Get the values from the EditText and Spinner views
            val cardnumber = txtCardNum.text.toString()
            val cardholder = txtHolder.text.toString()
            val cvvnum = txtCVV.text.toString()
            val expiredate = txtExpireDate.text.toString()
            var paycode = generateRandomCode(10)
            var amount = "1000"
            var username = "1000"

            val payId = database.child("payment").child(paycode).key
            // Create a new reservation object
            val paymentOb = Payment(payId.toString(), cardnumber, cardholder, cvvnum , expiredate ,amount ,username)

            if (payId != null) {
                database.child("payment").child(payId).setValue(paymentOb)
            }

            // Show a success message
            Toast.makeText(this, "Payment Adding successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Payment_gateway, Payment_done::class.java)
            startActivity(intent)
        }
    }

    data class Payment(
        val payId: String = "",
        val cardnumber: String = "",
        val cardholder: String = "",
        val cvvnum: String = "",
        val amount: String = "",
        val expiredate: String = "",
        val username: String?
    ) {
        constructor() : this("", "", "", "", "","" ,"")
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
        val intent = Intent(this@Payment_gateway, Items_for_cus::class.java)
        startActivity(intent)
    }

}