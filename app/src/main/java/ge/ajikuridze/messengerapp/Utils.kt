package ge.ajikuridze.messengerapp

import java.text.SimpleDateFormat
import java.util.*
import android.content.ContentResolver
import android.content.Context
import android.net.Uri

import androidx.annotation.AnyRes


class Utils {

    companion object {
        fun usernameToEmail(name: String): String {
            return "$name@example.com"
        }

        fun emailToUsername(email: String): String {
            return email.substringBefore("@")
        }

        fun formatAsDayOfMonth(timestamp: Long): String {
            val formatter = SimpleDateFormat("dd MM")

            return formatter.format(timestamp)
        }

        fun formatAsHourMinute(timestamp: Long): String {
            val formatter = SimpleDateFormat("hh:mm")

            return formatter.format(timestamp)
        }

        fun formatTime(timestamp: Long): String {
            if (formatAsDayOfMonth(timestamp) == formatAsDayOfMonth(Calendar.getInstance().timeInMillis)) {
                return formatAsHourMinute(timestamp)
            }

            return formatAsDayOfMonth(timestamp)
        }

        fun getUriToDrawable(
            context: Context,
            @AnyRes drawableId: Int
        ): Uri {
            return Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE +
                        "://" + context.getResources().getResourcePackageName(drawableId)
                        + '/' + context.getResources().getResourceTypeName(drawableId)
                        + '/' + context.getResources().getResourceEntryName(drawableId)
            )
        }
    }
}