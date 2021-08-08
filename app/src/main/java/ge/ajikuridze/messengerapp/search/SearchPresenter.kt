package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account


class SearchPresenter(val view: ISearchView): ISearchPresenter {

    override fun filterAccounts(filterStr: String) {
        TODO("Not yet implemented")
    }

    override fun filteredAccounts(filterStr: String, accounts: ArrayList<Account>) {
        view.filteredAccounts(accounts)
    }
}