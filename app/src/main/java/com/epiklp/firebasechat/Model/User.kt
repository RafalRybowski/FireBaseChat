package com.epiklp.firebasechat.Model

data class User(var login: String, var email: String, var phone : String, var photo : String, var online : Boolean,
                var servers : ArrayList<String>?, var friend : ArrayList<String>?)
