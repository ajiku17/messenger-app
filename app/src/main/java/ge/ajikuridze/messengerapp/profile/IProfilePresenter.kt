package ge.ajikuridze.messengerapp.profile

import android.graphics.Bitmap
import android.net.Uri
import ge.ajikuridze.messengerapp.models.Account

interface IProfilePresenter {
    fun fetchCurrentUser()
    fun currentUserFetched(acc: Account?)

    fun signOut()
    fun updateAccount(name: String, profession: String)
    fun accountUpdated(result: Boolean)
    fun updateAccountAvatar(uri: Uri)
    fun avatarUpdated(local: Uri?)
    fun fetchAvatar()
    fun avatarFetched(localUri: Uri?)
}