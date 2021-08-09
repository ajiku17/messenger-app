package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.Account

interface ILoginView {
    fun currentUserFetched(account: Account?)
    fun onLoginResult(result: Boolean)
    fun userRegistered(accunt: Account?, result: Boolean)
}