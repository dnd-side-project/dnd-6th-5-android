package com.fork.spoonfeed.data

import kotlin.properties.Delegates

object UserData {

    var id by Delegates.notNull<Int>()
    lateinit var accessToken: String
    lateinit var refreshToken: String
    lateinit var platform: String
}
