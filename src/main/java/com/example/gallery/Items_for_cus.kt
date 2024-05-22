package com.example.gallery

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Items_for_cus : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CusItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_for_cus)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val database = FirebaseDatabase.getInstance().reference
        val itemdata = mutableListOf<Item>()
        adapter = CusItemAdapter(itemdata)
        recyclerView.adapter = adapter

        database.child("item").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                itemdata.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val reservation = postSnapshot.getValue(Item::class.java)
                    reservation?.let {
                        itemdata.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(ContentValues.TAG, "Failed to read value.", databaseError.toException())
            }
        })
    }

    data class Item(
        val itemId: String = "",
        val itemname: String = "",
        val price: String = "",
        val imagename: String?
    ) {
        constructor() : this("", "", "", "")
    }

    fun back(view: View) {
        val intent = Intent(this@Items_for_cus, Dashboard::class.java)
        startActivity(intent)
    }

}