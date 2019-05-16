package com.epiklp.firebasechat.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epiklp.firebasechat.Model.Friend
import com.epiklp.firebasechat.R
import com.epiklp.firebasechat.utils.Common
import kotlinx.android.synthetic.main.friend_list.view.*

class FriendListAdapter : RecyclerView.Adapter<FriendListAdapter.MyViewHolder>() {

    var friendList : Map<String,Friend> = mutableMapOf()
    var keyList : List<String> = mutableListOf()
    private lateinit var context: Context

    fun MyCommicAdapter(context: Context, friendList: MutableMap<String,Friend> = mutableMapOf()) {
        this.context = context
        this.friendList = friendList
    }

    fun setData(list: Map<String, Friend>, toList: List<String>) {
        Log.d("list", list.toString())
        friendList = list
        keyList = toList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.friend_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = friendList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(keyList.isNotEmpty()) {
            if(Common.user.friend.contains(keyList[position]))
                friendList[keyList[position]]?.let { holder.setFriendList(it, true) }
            else
                friendList[keyList[position]]?.let { holder.setFriendList(it, false, keyList[position])}
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setFriendList(friend: Friend, s: Boolean, key : String = ""){
            itemView.nick_name.text = friend.login
            if(!s) {
                itemView.add_friend_button.visibility = View.VISIBLE
                itemView.add_friend_button.setOnClickListener {
                    itemView.add_friend_button.visibility = View.GONE
                    Common.user.friend.add(key)
                }
            }
        }
    }

}

