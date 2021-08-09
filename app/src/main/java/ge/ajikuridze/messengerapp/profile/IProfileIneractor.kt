package ge.ajikuridze.messengerapp.profile

interface IProfileIneractor {
    fun fetchCurrentUser()
    fun signOut()
    fun updateCurrentAccount(email: String, name: String, profession: String)
}