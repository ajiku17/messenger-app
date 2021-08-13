package ge.ajikuridze.messengerapp.search

import java.io.File

interface ISearchInteractor {

    fun filterAccounts(filterStr: String)
    fun fetchAvatarOf(id: String)

}