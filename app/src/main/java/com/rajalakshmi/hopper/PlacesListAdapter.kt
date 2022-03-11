package com.rajalakshmi.hopper

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.net.URI
import java.net.URL
import android.graphics.BitmapFactory
import android.widget.RatingBar
import java.io.InputStream


class PlacesListAdapter(private val context: Context, private val items: ArrayList<PlacesInfo>): RecyclerView.Adapter<PlacesListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var layout: LinearLayout  = view.findViewById(R.id.ll_places)
        var tvPlaceName =  view.findViewById<TextView>(R.id.tv_PlaceName)
        var ivPlaceImage =  view.findViewById<ImageView>(R.id.iv_placeImage)
        var tvDistance = view.findViewById<TextView>(R.id.tv_Distance)
        var drating = view.findViewById<RatingBar>(R.id.ratingBar)
        var tvRating = view.findViewById<TextView>(R.id.tv_Rating)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return  ViewHolder(
            LayoutInflater.from(context).inflate
                (R.layout.places,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]

        val placeName: String = model.placeName
        val placeDistance: String = model.distance
        val placeImage: String = model.placeImage

        Log.i("IMAGE",placeImage.toString())
        val rating: Float = model.rating
        holder.tvPlaceName.text = placeName
        holder.tvDistance.text = placeDistance
        holder.drating.rating = rating
//        val `in`: InputStream = URL("https://firebasestorage.googleapis.com/v0/b/agiledevs-89528.appspot.com/o/Gangotri.webp?alt=media&token=b1c7be76-634d-4432-b29c-62d21d716c31").openStream()
//        var bimage = BitmapFactory.decodeStream(`in`)
//        holder.ivPlaceImage.setImageBitmap(bimage)
        Picasso
            .get()
            .load("https://firebasestorage.googleapis.com/v0/b/agiledevs-89528.appspot.com/o/Gangotri.webp?alt=media&token=b1c7be76-634d-4432-b29c-62d21d716c31")
            .into(holder.ivPlaceImage)
        //holder.ivPlaceImage.setImageBitmap(get "https://firebasestorage.googleapis.com/v0/b/agiledevs-89528.appspot.com/o/Gangotri.webp?alt=media&token=b1c7be76-634d-4432-b29c-62d21d716c31".toUri())
        holder.tvRating.text = rating.toString()

        holder.layout.setOnClickListener {
            val intent = Intent(context,DescriptionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        Log.i("SIZE",items.size.toString())
        return items.size
    }
}