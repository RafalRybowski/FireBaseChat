package com.epiklp.firebasechat.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epiklp.firebasechat.Model.Friend

class FriendListAdapter : RecyclerView.Adapter<FriendListAdapter.MyViewHolder>() {

    lateinit var friendList : List<Friend>
    private lateinit var context: Context

    fun MyCommicAdapter(context: Context, friendList: List<Friend>) {
        this.context = context
        this.friendList = friendList
    }

    fun setData(list: List<Friend>) {
        friendList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = friendList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

