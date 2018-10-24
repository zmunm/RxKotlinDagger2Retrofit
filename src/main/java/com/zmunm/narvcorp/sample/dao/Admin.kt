package com.zmunm.narvcorp.sample.dao

class Admin (
        var id:String,
        var password:String?=null,
        var name:String?=null,
        var email:String?=null
){
    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val PASSWORD = "password"
        const val EMAIL = "email"
    }
}