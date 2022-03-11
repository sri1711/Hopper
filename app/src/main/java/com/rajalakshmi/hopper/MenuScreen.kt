package com.rajalakshmi.hopper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_screen)


        findViewById<Button>(R.id.btn_explore_surr).setOnClickListener {
            val intent = Intent(this,Places::class.java)
            startActivity(intent)
        }
    }
}