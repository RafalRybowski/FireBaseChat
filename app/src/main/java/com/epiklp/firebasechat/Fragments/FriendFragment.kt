package com.epiklp.firebasechat.Fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.epiklp.firebasechat.Model.User
import com.epiklp.firebasechat.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.friend_layout.view.*


class FriendFragment : Fragment() {
    lateinit var myView : View
    lateinit var dbRef : DatabaseReference

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.friend_layout , container, false)
        myView.search_friend.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if(s.length > 3)
                        searchFriend(s.toString())
                }
            }

        })

        dbRef = FirebaseDatabase.getInstance().reference
        return myView
    }

    fun searchFriend(query : String) = dbRef.child("Users").addValueEventListener(object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {
            Log.d("error", p0.toString())
        }

        override fun onDataChange(p0: DataSnapshot) {
            if(p0.hasChildren())
                collectSearchFriends(p0.children, query)
        }
    })

    private fun collectSearchFriends(children: MutableIterable<DataSnapshot>, query : String) {
        children.forEach{ it ->
            Log.d("children", it.key)
            val user = it.getValue(User::class.java)
            user?.let {
                if(it.login.contains(query, true)) Log.d("children", it.login)
            }
        }
    }
}