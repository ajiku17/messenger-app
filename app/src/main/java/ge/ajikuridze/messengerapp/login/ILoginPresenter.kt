package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.User

interface ILoginPresenter {
    fun getCurrentUser(): User?
    fun loginUser(username: String, password: String): Boolean
    fun isUserLoggedIn(): Boolean
    fun registerUser(name: String, pass: String, profession: String): Boolean
}