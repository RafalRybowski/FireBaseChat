package com.epiklp.firebasechat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.epiklp.firebasechat.utils.IFirebaseLoginDone
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pd.chocobar.ChocoBar
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), IFirebaseLoginDone {

    @SuppressLint("WrongConstant")
    override fun onFireBaseLoginSuccess() {
        ChocoBar.builder().setActivity(this@MainActivity).green().setText("Login Success").setDuration(ChocoBar.LENGTH_SHORT).show()
        updateUI(mAuth.currentUser)
    }

    @SuppressLint("WrongConstant")
    override fun onFireBaseLoginFail() {
        ChocoBar.builder().setActivity(this@MainActivity).red().setText("Login Failed").setDuration(ChocoBar.LENGTH_SHORT).show()
        login_login.error = "Wrong Login"
        login_login.requestFocus()
        login_password.error = "Wrong Password"
        login_password.requestFocus()
        updateUI(null)
    }

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
        if(email.isEmpty()){
            login_login.error = "Login Can't Be Empty"
            login_login.requestFocus()
            return
        }

        if(password.isEmpty()){
            login_password.error = "Login Can't Be Empty"
            login_password.requestFocus()
            return
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                onFireBaseLoginSuccess()
            }else{
                onFireBaseLoginFail()
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
