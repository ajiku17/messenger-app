package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.Account

class AccountPresenter(var view: ILoginView): ILoginPresenter {

    private val interactor: ILoginInteractor = AccountInteractor(this)

    override fun isUserLoggedIn(): Boolean {
        return interactor.getCurrentUser() != null
    }

    override fun registerUser(name: String, pass: String, profession: String): Boolean {
        return interactor.registerUser(name, pass, profession)
    }

    override fun getCurrentUser(): Account? {
        return interactor.getCurrentUser()
    }

    override fun loginUser(username: String, password: String): Boolean {
        if (interactor.validateUser(username, password)) {
            interactor.loginUser(username, password)
        }

        return false
    }



}