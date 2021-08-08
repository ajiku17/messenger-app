package ge.ajikuridze.messengerapp.profile

import ge.ajikuridze.messengerapp.models.Account

interface IProfilePresenter {
    fun fetchCurrentUser()
    fun currentUserFetched(acc: Account)
}