package ge.ajikuridze.messengerapp.search

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ajikuridze.messengerapp.models.Account

class SearchInteractor(val presenter: ISearchPresenter): ISearchInteractor {

    private val accounts = Firebase.database.getReference("accounts")
    private val auth = Firebase.auth

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

}