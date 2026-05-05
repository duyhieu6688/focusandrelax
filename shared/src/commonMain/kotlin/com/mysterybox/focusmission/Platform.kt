package com.mysterybox.focusmission

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform