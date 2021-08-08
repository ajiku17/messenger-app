package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.Account

interface ILoginPresenter {
    fun getCurrentUser(): Account?
    fun loginUser(username: String, password: String): Boolean
    fun isUserLoggedIn(): Boolean
    fun registerUser(name: String, pass: String, profession: String): Boolean
}