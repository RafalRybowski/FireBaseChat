package com.epiklp.firebasechat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pd.chocobar.ChocoBar
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        button_sign_up.setOnClickListener{singUp()}

        login_registration.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))
        }
    }

    @SuppressLint("WrongConstant")
    private fun singUp() {
        val email = login_login.text.toString()
        val password = login_password.text.toString()

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                ChocoBar.builder().setActivity(this@MainActivity).green().setText("Login Success").setDuration(ChocoBar.LENGTH_SHORT).show()
                val user = mAuth.currentUser
                updateUI(user)
            }else{
                ChocoBar.builder().setActivity(this@MainActivity).red().setText("Login Failed").setDuration(ChocoBar.LENGTH_SHORT).show()
            }
        }
    }


    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            startActivity(Intent(this@MainActivity, ChatActivity::class.java))
        }
    }


}
