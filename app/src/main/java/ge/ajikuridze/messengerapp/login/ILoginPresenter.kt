package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.Account

interface ILoginPresenter {
    fun loginUser(username: String, password: String)
    fun onLoginResult(result: Boolean)

    fun isUserLoggedIn(): Boolean

    fun registerUser(account: Account, pass: String)
    fun userRegistered(account: Account, result: Boolean)
}