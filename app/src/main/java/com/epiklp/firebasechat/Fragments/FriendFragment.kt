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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.epiklp.firebasechat.Adapter.FriendListAdapter
import com.epiklp.firebasechat.R
import kotlinx.android.synthetic.main.friend_layout.view.*


class FriendFragment : Fragment() {
    lateinit var myView : View
    val listAdapter : FriendListAdapter = FriendListAdapter()
    val friendViewModel : MyFragmentViewModel = MyFragmentViewModel()

    @SuppressLint("WrongConstant")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.friend_layout , container, false)

        myView.recyclerViewFriend.apply{
            layoutManager = LinearLayoutManager(this@FriendFragment.context)
            adapter  = listAdapter

        }

        myView.recyclerViewFriend.layoutManager = LinearLayoutManager(this@FriendFragment.context)
        myView.recyclerViewFriend.adapter = listAdapter
        friendViewModel.searchFriend().observe(this ,Observer {
            listAdapter.setData(it, it.keys.toList())
            Log.d("list1", it.toString() + it.keys.toList())
            listAdapter.notifyDataSetChanged()
        })

        myView.search_friend.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let{
                    if(s.isEmpty())
                        fetchImage("Owned")
                    if(s.length > 2)
                        fetchImage(s.toString())
                }
            }

        })
        fetchImage("Owned")
        return myView
    }

    private fun fetchImage(query : String){
        friendViewModel.searchFriend(query)
    }


}