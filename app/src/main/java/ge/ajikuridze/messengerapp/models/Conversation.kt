package ge.ajikuridze.messengerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class Conversation (
    var id: String? = null,
    var lastMessage: Message? = null,
    var messages: HashMap<String, Message>? = null,
    var dummy: Int? = null,
) : Parcelable {

}