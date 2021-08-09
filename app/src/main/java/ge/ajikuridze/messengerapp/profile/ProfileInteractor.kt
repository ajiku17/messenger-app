package ge.ajikuridze.messengerapp.profile

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.models.Account

class ProfileInteractor(var presenter: IProfilePresenter): IProfileIneractor {

    private val auth: FirebaseAuth = Firebase.auth
    private val accounts = FirebaseDatabase.getInstance().getReference("accounts")

    override fun fetchCurrentUser() {
        accounts.child(auth.currentUser?.uid!!).get().addOnSuccessListener { userSnapshot ->
            val acc = userSnapshot.getValue<Account>()
            presenter.currentUserFetched(acc)
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun updateCurrentAccount(email: String, name: String, profession: String) {
        auth.currentUser!!.updateEmail(email)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    accounts.child(auth.currentUser!!.uid)
                        .updateChildren(mapOf("name" to name, "profession" to profession))
                        .addOnCompleteListener { task ->
                            presenter.accountUpdated(task.isSuccessful)
                        }

                } else {
                    presenter.accountUpdated(false)
                }
            }
    }
}