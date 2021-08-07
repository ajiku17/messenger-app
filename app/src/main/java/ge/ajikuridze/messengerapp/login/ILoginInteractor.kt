package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.*

interface ILoginInteractor {
    fun getCurrentUser(): User?
    fun validateUser(username: String, password: String): Boolean
    fun loginUser(username: String, password: String)
    fun registerUser(name: String, pass: String, profession: String): Boolean
}