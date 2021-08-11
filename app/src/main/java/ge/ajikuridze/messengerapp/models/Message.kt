package ge.ajikuridze.messengerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import java.text.SimpleDateFormat
import java.util.*

@IgnoreExtraProperties
@Parcelize
class Message(
    @get:Exclude
    var convUid: String? = null,
    var sender: String? = null,
    val text: String? = null,
    @set:Exclude
    var sent: Boolean? = null,
    val timestamp: Long? = Calendar.getInstance().timeInMillis,
) : Parcelable {

}