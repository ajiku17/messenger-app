package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.models.Account

class AccountPresenter(var view: ILoginView): ILoginPresenter {

    private val interactor: ILoginInteractor = AccountInteractor(this)

    override fun isUserLoggedIn(): Boolean {
        return interactor.currentUserExists()
    }

    override fun registerUser(account: Account, pass: String) {
        interactor.registerUser(Utils.usernameToEmail(account.name!!), account, pass)
    }

    override fun userRegistered(account: Account, result: Boolean) {
        view.userRegistered(account, result)
    }

    override fun loginUser(username: String, password: String) {
        interactor.loginUser(Utils.usernameToEmail(username), password)
    }

    override fun onLoginResult(result: Boolean) {
        view.onLoginResult(result)
    }

}