package com.example.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class LineIn : AppCompatActivity() {

    private lateinit var txtUserName: EditText
    private lateinit var txtQty: EditText
    private lateinit var txtProduct: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_in)

    }
}