package ge.ajikuridze.messengerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import java.text.SimpleDateFormat
import java.util.*

@IgnoreExtraProperties
@kotlinx.parcelize.Parcelize
class Message(
    @get:Exclude
    var convUid: String? = null,
    @get:Exclude
    var received: Boolean? = true,
    var sender: String? = null,
    val timestamp: Long? = Calendar.getInstance().timeInMillis,
    val text: String? = null,
) : Parcelable {


    @IgnoredOnParcel
    private val formatter = SimpleDateFormat("hh:mm")

    @Exclude
    fun getTimeAsCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp!!

        return calendar
    }

    @Exclude
    fun getFormattedTime(): String {
        return formatter.format(timestamp)
    }
}