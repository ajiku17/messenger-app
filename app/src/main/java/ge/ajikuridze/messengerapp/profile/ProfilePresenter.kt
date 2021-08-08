package ge.ajikuridze.messengerapp.profile

import ge.ajikuridze.messengerapp.models.Account

class ProfilePresenter(var view: IProfileView): IProfilePresenter {

    private val interactor: IProfileIneractor = ProfileInteractor(this)

    override fun fetchCurrentUser() {
        interactor.fetchCurrentUser()
    }

    override fun currentUserFetched(acc: Account) {
        view.userFetched(acc)
    }
}