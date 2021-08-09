package ge.ajikuridze.messengerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class Account(
    @get:Exclude
    var id: String? = null,
    var name: String? = null,
    var profession: String? = null,
    var conversations: Map<String, String>? = null,
//    val avatar: Image,
) : Parcelable {

}
