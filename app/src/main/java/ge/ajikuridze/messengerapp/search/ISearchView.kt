package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account

interface ISearchView {
    fun filteredAccounts(data: ArrayList<Account>)
}