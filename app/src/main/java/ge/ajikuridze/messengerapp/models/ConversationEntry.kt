package ge.ajikuridze.messengerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@kotlinx.parcelize.Parcelize
data class ConversationEntry (
    @get:Exclude
    var uid: String? = null,
    var lastMessage: Message? = null,
    var messages: MutableList<Message>? = null,
) : Parcelable {

}
