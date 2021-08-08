package ge.ajikuridze.messengerapp.search

import android.accounts.Account

interface ISearchInteractor {

    fun filterAccounts(filterStr: String)
    fun filteredAccoutnts(filterStr: String, accoutns: ArrayList<Account>)

}