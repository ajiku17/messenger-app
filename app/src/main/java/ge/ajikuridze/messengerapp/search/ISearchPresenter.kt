package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account
import java.io.File


interface ISearchPresenter {
    fun filterAccounts(filterStr: String)
    fun filteredAccounts(filterStr: String, accounts: ArrayList<Account>)
    fun fetchAvatarOf(id: String)
    fun avatarFetched(file: File?, id: String)
}