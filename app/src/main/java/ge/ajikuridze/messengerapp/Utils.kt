package ge.ajikuridze.messengerapp

import com.google.firebase.database.Exclude
import kotlinx.parcelize.IgnoredOnParcel
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

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
    }
}