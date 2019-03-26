package com.epiklp.firebasechat.Model

class Friend {
    var login: String = ""
    var email: String = ""
    var phone : String = ""
    var photo : String = ""

    constructor(login: String, email: String, phone: String, photo: String) {
        this.login = login
        this.email = email
        this.phone = phone
        this.photo = photo
    }
}