package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account

interface SearchListClickListener {
    fun accountClicked(acc: Account)
}