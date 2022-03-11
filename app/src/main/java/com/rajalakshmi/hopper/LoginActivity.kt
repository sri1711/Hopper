package com.rajalakshmi.hopper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        findViewById<Button>(R.id.btn_Login).setOnClickListener {
            Login()
        }

    }

    private fun Login() {
        var email = findViewById<EditText>(R.id.et_email).text.toString().trim()
        Log.i("SRI1711",email)
        var password = findViewById<EditText>(R.id.et_password).text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Log.d("SRI1711", "signInWithEmail:success")
                    val user = auth.currentUser
                    Log.i("SRI1711",user!!.email.toString())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SRI1711", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}