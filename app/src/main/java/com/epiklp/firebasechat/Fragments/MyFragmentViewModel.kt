package com.epiklp.firebasechat.Fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epiklp.firebasechat.Model.Friend
import com.epiklp.firebasechat.Model.User
import com.epiklp.firebasechat.utils.Common
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyFragmentViewModel : ViewModel(){
    private var friendList : MutableLiveData<Map<String, Friend>> = MutableLiveData()
    private var dbRef = FirebaseDatabase.getInstance().reference

    fun searchFriend(query : String = "") : LiveData<Map<String, Friend>> {
        if (query == "Owned" || query.isEmpty() || query.isBlank()) {
            if(Common.friends.isEmpty())
                friendList.postValue(ownedFriend())
            else
                friendList.postValue(Common.friends)
        } else {
            friendList.postValue(lookNewFriend(query))
        }
        return friendList
    }

        private fun ownedFriend(): Map<String, Friend> {
            dbRef.child("Users").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("error", p0.toString())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.hasChildren())
                        Common.user.friend.forEach {
                            if (p0.hasChild(it)) {
                                p0.child(it).getValue(User::class.java)?.let{ user ->
                                    Common.friends[it] = user.toFriend()

                                }
                            }
                        }
                }
            })
            return  Common.friends
        }

        private fun lookNewFriend(query : String): Map<String, Friend> {
            val search : MutableMap<String,Friend> = mutableMapOf()
            dbRef.child("Users").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("error", p0.toString())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.hasChildren())
                        p0.children.forEach {
                            val user = it.getValue(User::class.java)
                            user?.let { current ->
                                if(current.email != Common.user.email)
                                    if (current.login.startsWith(query)) {
                                        search[it.key.toString()] = user.toFriend()
                                    }
                            }
                        }
                }
            })
            return search
        }
}
