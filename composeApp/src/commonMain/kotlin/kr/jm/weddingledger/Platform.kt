package kr.jm.weddingledger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform