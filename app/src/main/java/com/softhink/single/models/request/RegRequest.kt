package com.softhink.single.models.request

data class RegRequest(var name: String?, var date: String?, var gender: String?,
                      var email: String?, var pss: String?, var imageProfile: String?) : BaseRequest(){

    constructor() : this(null, null, null, null, null, null)
}