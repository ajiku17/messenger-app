package ge.ajikuridze.messengerapp

class Utils {

    companion object {
        fun usernameToEmail(name: String): String {
            return "$name@example.com"
        }

        fun emailToUsername(email: String): String {
            return email.substringBefore("@")
        }
    }
}