package com.epiklp.firebasechat.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.epiklp.firebasechat.ChatActivity
import com.epiklp.firebasechat.R
import com.pd.chocobar.ChocoBar

class FriendFragment : Fragment() {

    lateinit var myView : View

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.friend_layout, container, false)
        val addImage : ImageView = myView.findViewById(R.id.add_image)
        addImage.setOnClickListener(View.OnClickListener{
                searchFriend()
        })

        return myView
    }

    fun searchFriend(){

    }
}