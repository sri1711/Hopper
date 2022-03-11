package com.rajalakshmi.hopper

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Places : AppCompatActivity() {
    private var itemAdapter: PlacesListAdapter? = null
    private var placeList: ArrayList<PlacesInfo>? = null
    private var rating: RatingBar? = null
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)
        placeList = ArrayList<PlacesInfo>()

        database = Firebase.database.reference

//        districtList = intent.getSerializableExtra("districtList") as ArrayList<districinfo>?
//        districtList = ArrayList<districinfo>()
//

//        Log.i("SIZE",placeList!!.size.toString())

        val place1 = PlacesInfo(
            "Punjabi Dhaba",
            "https://firebasestorage.googleapis.com/v0/b/agiledevs-89528.appspot.com/o/Gangotri.webp?alt=media&token=b1c7be76-634d-4432-b29c-62d21d716c31",
            "21 KM",
            4.2f,
        )

        val Ref = FirebaseDatabase.getInstance().getReference()
        Ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children

                Log.i("SRI1711","INNNNNNNNNNNNNNNNNNNN")
                for(each in children){
                    Log.i("SRIDOC2_SKHST_pending", "${each.value}")
                    //pendingAppointments?.add(each.child("toUserID").value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
        })


        placeList!!.add(place1)

        for(i in 0 until placeList!!.size){
            Log.i("VIJ", placeList!![i].placeName)
        }
        setUpRecyclerView(placeList!!)
    }
    private fun setUpRecyclerView(placeList: ArrayList<PlacesInfo>) {
        var rvPlaceList = findViewById<RecyclerView>(R.id.rvPlaceWiseInfo)

        rvPlaceList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        itemAdapter = PlacesListAdapter(this, placeList)

        rvPlaceList.adapter = itemAdapter
    }

}