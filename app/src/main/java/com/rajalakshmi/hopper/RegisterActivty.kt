package com.rajalakshmi.hopper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class RegisterActivty : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_activty)
        auth = Firebase.auth
        findViewById<Button>(R.id.btn_Register).setOnClickListener {
            Register()
        }

    }

    private fun Register() {
        var email = findViewById<EditText>(R.id.et_mobile_no).text.toString()
        var password = findViewById<EditText>(R.id.et_password).text.toString()
        var name = findViewById<EditText>(R.id.et_name).text.toString()

        if(!password.isEmpty() && !email.isEmpty()){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user =  Firebase.auth.currentUser!!

                        val profileUpdates = userProfileChangeRequest {
                            displayName = name

                        }

                        user!!.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("SRI1711", "User profile updated.")
                                    Toast.makeText(this,"Registered Successfully!!",Toast.LENGTH_SHORT).show()
                                }

                            }

                        Log.d("SRI1711", "createUserWithEmail:success")
//                        val user = auth.currentUser
                        Log.i("SRI1711",user!!.email.toString())
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SRI1711", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}