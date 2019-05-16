package com.epiklp.firebasechat.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.epiklp.firebasechat.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.profile_layout.view.*

class ProfileFragment: Fragment(){

    lateinit var myView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.profile_layout, container, false)
        initView()
        return myView
    }

    private fun initView(){
        myView.log_out.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
        }
    }

}