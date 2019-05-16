package com.epiklp.firebasechat.Model

import java.io.Serializable

class User : Serializable{
    var login: String = ""
    var email: String = ""
    var phone : String = ""
    var photo : String = ""
    var online : Boolean = false
    var servers : MutableList<String> = mutableListOf()
    var friend : MutableList<String> = mutableListOf()

    constructor(){}

    constructor(login: String, email: String, phone : String, photo : String, online : Boolean, servers : MutableList<String> = mutableListOf(), friend : MutableList<String> = mutableListOf()){
        this.login = login
        this.email = email
        this.phone = phone
        this.photo = photo
        this.online = online
        this.servers = servers
        this.friend = friend
    }

    fun toFriend() : Friend = Friend(login, email, phone, photo)

}
