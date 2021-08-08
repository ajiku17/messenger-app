package ge.ajikuridze.messengerapp.login

import ge.ajikuridze.messengerapp.models.Account

class AccountInteractor(var presenter: AccountPresenter): ILoginInteractor {
    override fun getCurrentUser(): Account? {
        return null
    }

    override fun validateUser(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun loginUser(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun registerUser(name: String, pass: String, profession: String): Boolean {
        TODO("Not yet implemented")
    }
}