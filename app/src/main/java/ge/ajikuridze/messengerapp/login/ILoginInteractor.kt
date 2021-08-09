package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.*

interface ILoginInteractor {
    fun currentUserExists(): Boolean
    fun loginUser(username: String, password: String)
    fun registerUser(email: String, account: Account, pass: String)
}