package com.example.gallery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CusItemAdapter(private val item: List<Items_for_cus.Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_one_item_for_customer, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = item[position]
        val reservationViewHolder = holder as ReservationViewHolder
        reservationViewHolder.bind(reservation)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label_name: TextView = itemView.findViewById(R.id.lbl_name)
        private val label_price: TextView = itemView.findViewById(R.id.lbl_price)
        private val item_img: ImageView = itemView.findViewById(R.id.item_image)
        private val context = itemView.context

        var add_button: Button? = null

        fun bind(item: Items_for_cus.Item) {
            label_name.text = item.itemname
            label_price.text = item.price
            Glide.with(itemView.context).load(item.imagename).into(item_img)

            add_button = itemView.findViewById<Button>(R.id.add_to_cart_button)
            add_button?.setOnClickListener {
                val intent = Intent(context, Add_to_cart::class.java)

                //intent.putExtra("txtReviewPostId", item.itemId)
//              intent.putExtra("item_price", item.price)
//              intent.putExtra("item_image", item.imagename)
                context.startActivity(intent)

            }
        }
    }
}
