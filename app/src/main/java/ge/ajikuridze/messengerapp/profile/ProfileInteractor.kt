package ge.ajikuridze.messengerapp.profile

import ge.ajikuridze.messengerapp.models.Account

class ProfileInteractor(var presenter: IProfilePresenter): IProfileIneractor {

    override fun fetchCurrentUser() {
        presenter.currentUserFetched(Account("bla"))
    }
}