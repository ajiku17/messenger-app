package ge.ajikuridze.messengerapp.search

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ajikuridze.messengerapp.avatarfetcher.AvatarFetcher
import ge.ajikuridze.messengerapp.avatarfetcher.AvatarListener
import ge.ajikuridze.messengerapp.models.Account
import java.io.File

class SearchInteractor(val presenter: ISearchPresenter): ISearchInteractor, AvatarListener {

    private val accounts = Firebase.database.getReference("accounts")
    private val auth = Firebase.auth
    private val avatarFetcher = AvatarFetcher(this)

    override fun filterAccounts(filterStr: String) {
        accounts.get().addOnSuccessListener { snap ->
            var accs = snap.getValue<MutableMap<String, Account>>()
            accs = accs?.filter { (_, acc) ->
                if (filterStr.isBlank()) {
                    return@filter true
                } else {
                    if (!acc.name.isNullOrBlank()) {
                        return@filter acc.name!!.contains(filterStr)
                    }

                    return@filter false
                }
            } as MutableMap<String, Account>?

            accs?.apply {
                remove(auth.currentUser?.uid)
                forEach { (key, acc) ->
                    acc.id = key
                }
                values.toMutableList().let { result ->
                    presenter.filteredAccounts(filterStr, result as ArrayList<Account>)
                }
            }
        }
    }

    override fun fetchAvatarOf(id: String) {
        avatarFetcher.fetchAvatarById(id)
    }

    override fun avatarFetched(file: File?, id: String) {
        presenter.avatarFetched(file, id)
    }

}