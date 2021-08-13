package ge.ajikuridze.messengerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class Account(
    var id: String? = null,
    var avatar: String? = null,
    var name: String? = null,
    var profession: String? = null,
    var convToUserId: Map<String, String>? = null,
) : Parcelable {

}
