package ge.ajikuridze.messengerapp.profile

import android.graphics.Bitmap
import android.net.Uri
import ge.ajikuridze.messengerapp.models.Account

interface IProfileView {
    fun userFetched(acc : Account?)
    fun accountUpdated(result: Boolean)
    fun avatarUpdated(localUri: Uri?)
    fun avatarFetched(localUri: Uri?)
}