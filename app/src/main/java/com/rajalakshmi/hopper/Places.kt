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
import com.google.firebase.database.ktx.getValue
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



        val Ref = FirebaseDatabase.getInstance().getReference("/Temples")

        Log.i("SRI1711",Ref.toString())

        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("SRI1711","INNNNNNNNNNNNNNNNNNNN")
                val children = snapshot.children
//                Log.i("SRI1711",children.toString())

                for(each in children){
                    var key = each.children

                    for (each in key){
                        Log.i("SRIDOC2_SKHST_pending", "${each.child("Description").value}")

                        placeList!!.add(PlacesInfo("${each.key}","${each.child("Photo").value}",
                            "${each.child("Description").value}",each.child("Rating").value.toString().toFloat()))
                    }
                    Log.i("SRIDOC2_SKHST_pending", "${key}")
                    //pendingAppointments?.add(each.child("toUserID").value.toString())
                }
                setUpRecyclerView(placeList!!)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("SRI1711",error.message)
            }
        })




        for(i in 0 until placeList!!.size){
            Log.i("VIJ", placeList!![i].placeName)
        }

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