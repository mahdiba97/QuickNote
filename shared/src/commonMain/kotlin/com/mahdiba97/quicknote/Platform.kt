package com.mahdiba97.quicknote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform