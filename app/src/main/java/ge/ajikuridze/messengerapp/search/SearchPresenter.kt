package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.ConversationEntry


class SearchPresenter(val view: ISearchView): ISearchPresenter {

    private var interactor: ISearchInteractor = SearchInteractor(this)

    override fun filterAccounts(filterStr: String) {
        interactor.filterAccounts(filterStr)
    }

    override fun filteredAccounts(filterStr: String, accounts: ArrayList<Account>) {
        view.filteredAccounts(accounts)
    }
}