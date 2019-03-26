package com.epiklp.firebasechat


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chatv2.*
import androidx.viewpager.widget.ViewPager
import com.epiklp.firebasechat.Adapter.myViewPageAdapter




class ChatActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatv2)

        initBottomBar()
    }

    private fun initBottomBar() {
        initViewPager()
        bottomBar.setOnTabSelectListener { tabId ->
            when(tabId){
                R.id.friends -> {
                    viewPager.setCurrentItem(0, true)
                }
                R.id.Chats -> {
                    viewPager.setCurrentItem(1, true)
                }
                R.id.Profile -> {
                    viewPager.setCurrentItem(2, true)
                }
            }
        }
    }

    private fun initViewPager(){
        viewPager.adapter = myViewPageAdapter(supportFragmentManager, 3)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                bottomBar.selectTabAtPosition(position)
            }

        })
    }


}
