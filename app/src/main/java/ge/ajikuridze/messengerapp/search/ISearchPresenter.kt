package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account


interface ISearchPresenter {
    fun filterAccounts(filterStr: String)
    fun filteredAccounts(filterStr: String, accounts: ArrayList<Account>)
}