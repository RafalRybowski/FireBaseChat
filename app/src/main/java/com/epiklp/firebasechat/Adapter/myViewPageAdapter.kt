package com.epiklp.firebasechat.Adapter


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.epiklp.firebasechat.Fragments.FriendFragment
import com.epiklp.firebasechat.Fragments.ProfileFragment
import com.epiklp.firebasechat.Fragments.ServerFragment


class myViewPageAdapter(fm : FragmentManager, numberOfTabs : Int) : FragmentStatePagerAdapter(fm) {

    var number : Int = numberOfTabs
    lateinit var fragment : Fragment
    var friendFragment = FriendFragment()
    var serverFragment = ServerFragment()
    var profileFragment = ProfileFragment()


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                fragment = friendFragment
            }
            1 -> {
                fragment = serverFragment
            }
            2 -> {
                fragment = profileFragment
            }
        }
        return fragment
    }


    override fun getCount() : Int = number
}