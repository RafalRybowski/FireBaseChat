package com.epiklp.firebasechat.utils

import com.epiklp.firebasechat.Model.Friend
import com.epiklp.firebasechat.Model.User

object Common{
    lateinit var user : User
    var friends : MutableMap<String,Friend> = mutableMapOf()


}