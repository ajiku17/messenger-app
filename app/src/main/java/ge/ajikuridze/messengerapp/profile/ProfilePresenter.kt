package ge.ajikuridze.messengerapp.profile

import android.graphics.Bitmap
import android.net.Uri
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.models.Account

class ProfilePresenter(var view: IProfileView): IProfilePresenter {

    private val interactor: IProfileIneractor = ProfileInteractor(this)

    override fun fetchCurrentUser() {
        interactor.fetchCurrentUser()
    }

    override fun currentUserFetched(acc: Account?) {
        view.userFetched(acc)
    }

    override fun signOut() {
        interactor.signOut()
    }

    override fun updateAccount(name: String, profession: String) {
        interactor.updateCurrentAccount(Utils.usernameToEmail(name), name, profession)
    }

    override fun accountUpdated(result: Boolean) {
        view.accountUpdated(result)
    }

    override fun updateAccountAvatar(uri: Uri) {
        interactor.updateAccountAvatar(uri)
    }

    override fun avatarUpdated(local: Uri?) {
        view.avatarUpdated(local)
    }

    override fun fetchAvatar() {
        interactor.fetchAvatar()
    }

    override fun avatarFetched(localUri: Uri?) {
        view.avatarFetched(localUri)
    }
}