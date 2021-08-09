package ge.ajikuridze.messengerapp.profile

import ge.ajikuridze.messengerapp.models.Account

interface IProfileView {
    fun userFetched(acc : Account?)
    fun accountUpdated(result: Boolean)
}