package com.epiklp.firebasechat.Adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.epiklp.firebasechat.Fragments.FriendFragment
import com.epiklp.firebasechat.Fragments.ProfileFragment
import com.epiklp.firebasechat.Fragments.ServerFragment


class myViewPageAdapter(fm : FragmentManager, numberOfTabs : Int) : FragmentStatePagerAdapter(fm) {

    var number : Int = numberOfTabs
    lateinit var fragment : Fragment

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                fragment = FriendFragment()
            }
            1 -> {
                fragment = ServerFragment()
            }
            2 -> {
                fragment = ProfileFragment()
            }
        }
        return fragment
    }


    override fun getCount() : Int = number
}