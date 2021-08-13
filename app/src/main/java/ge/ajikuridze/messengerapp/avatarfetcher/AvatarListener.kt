package ge.ajikuridze.messengerapp.avatarfetcher

import java.io.File

interface AvatarListener {
    fun avatarFetched(file: File?, id: String)
}