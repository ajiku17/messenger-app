package ge.ajikuridze.messengerapp.profile

import android.graphics.Bitmap
import android.net.Uri

interface IProfileIneractor {
    fun fetchCurrentUser()
    fun signOut()
    fun updateCurrentAccount(email: String, name: String, profession: String)
    fun updateAccountAvatar(uri: Uri)
    fun fetchAvatar()
}