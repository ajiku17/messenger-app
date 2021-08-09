package ge.ajikuridze.messengerapp.profile

import ge.ajikuridze.messengerapp.models.Account

interface IProfilePresenter {
    fun fetchCurrentUser()
    fun currentUserFetched(acc: Account?)

    fun signOut()
    fun updateAccount(name: String, profession: String)
    fun accountUpdated(result: Boolean)
}