package ge.ajikuridze.messengerapp.models

import android.net.Uri

data class ConversationPreview(var avatarUri: Uri? = null, var otherAcc: Account, var message: Message)
