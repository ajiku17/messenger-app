package ge.ajikuridze.messengerapp.models

import android.graphics.Bitmap

data class ConversationPreview(var avatarBitmap: Bitmap? = null, var otherAcc: Account, var message: Message)
