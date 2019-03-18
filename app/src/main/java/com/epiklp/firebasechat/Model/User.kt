package com.epiklp.firebasechat.Model

import java.io.Serializable

class User : Serializable{
    var login: String = ""
    var email: String = ""
    var phone : String = ""
    var photo : String = ""
    var online : Boolean = false
    var servers : ArrayList<String>? = null
    var friend : ArrayList<String>? = null

    constructor(){}

    constructor(login: String, email: String, phone : String, photo : String, online : Boolean, servers : ArrayList<String>?, friend : ArrayList<String>?){
        this.login = login
        this.email = email
        this.phone = phone
        this.photo = photo
        this.online = online
        this.servers = servers
        this.friend = friend
    }

}
