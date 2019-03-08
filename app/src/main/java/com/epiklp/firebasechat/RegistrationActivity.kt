package com.epiklp.firebasechat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.epiklp.firebasechat.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.pd.chocobar.ChocoBar
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()

        button_sign_up.setOnClickListener{register()}

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            finish()
        }
    }

    private fun register() {
        val login = registration_login.text.toString()
        val mail = registration_mail.text.toString()
        val password = registration_password.text.toString()
        val repeat_password = registration_repeat_password.text.toString()

        if(login.isEmpty()){
            registration_login.error = "Enter Valid Login Name"
            registration_login.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            registration_mail.error = "Enter Valid email"
            registration_mail.requestFocus()
            return
        }

        if(password.isEmpty()){
            registration_password.error = "Enter Valid Password"
            registration_password.requestFocus()
            return
        }

        if(password.length < 6){
            registration_password.error = "Enter Valid Password Shorter Than 6 Characters"
            registration_password.requestFocus()
            return
        }

        if( repeat_password != password){
            registration_repeat_password.error = "Enter Valid Repeat Password"
            registration_repeat_password.requestFocus()
            return
        }

        registerAccount(login, mail, password)
    }

    @SuppressLint("WrongConstant")
    fun registerAccount(login: String, mail: String, password: String) {
        auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                val user = User(login, mail, "", "")

                FirebaseDatabase.getInstance().getReference("Users").
                    child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user).addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        ChocoBar.builder().setActivity(this@RegistrationActivity).green().setText("Registration Success").setDuration(ChocoBar.LENGTH_SHORT).show()
                    } else {
                        ChocoBar.builder().setActivity(this@RegistrationActivity).red().setText("Can't register").setDuration(ChocoBar.LENGTH_SHORT).show()
                    }
                }

                updateUI(auth.currentUser)
            } else {
                ChocoBar.builder().setActivity(this@RegistrationActivity).red().setText("Can't register").setDuration(ChocoBar.LENGTH_SHORT).show()
                updateUI(null)
            }

        }
    }

}



