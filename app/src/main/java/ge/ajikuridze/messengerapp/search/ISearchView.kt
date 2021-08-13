package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account
import java.io.File

interface ISearchView {
    fun filteredAccounts(data: ArrayList<Account>)
    fun avatarFetched(file: File?, id: String)
}