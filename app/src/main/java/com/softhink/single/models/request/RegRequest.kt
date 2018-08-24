package com.softhink.single.models.request

class RegRequest(name: String, date: String?, gender: String) : BaseRequest() {
    var fullName = name
    var birthdate = date
    var sex = gender
    lateinit var email: String
    lateinit var password: String
    lateinit var imageProfile: String
}