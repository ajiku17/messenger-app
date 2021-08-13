package ge.ajikuridze.messengerapp.profile

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.models.Account
import java.io.File

class ProfileInteractor(var presenter: IProfilePresenter): IProfileIneractor {

    private val auth: FirebaseAuth = Firebase.auth
    private val accounts = FirebaseDatabase.getInstance().getReference("accounts")
    private val imageStore = FirebaseStorage.getInstance().getReference("images")

    override fun fetchCurrentUser() {
        accounts.child(auth.currentUser?.uid!!).get().addOnSuccessListener { userSnapshot ->
            val acc = userSnapshot.getValue<Account>()
            presenter.currentUserFetched(acc)
        }
    }

    override fun fetchAvatar() {
        val avatarRef = imageStore.child(auth.currentUser!!.uid)

        val localFile = File.createTempFile("avatar", "jgp")
        avatarRef.getFile(localFile).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                presenter.avatarFetched(Uri.fromFile(localFile))
            } else {
                presenter.avatarFetched(null)
            }
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

    override fun updateAccountAvatar(uri: Uri) {
        val avatarRef = imageStore.child(auth.currentUser!!.uid)

        avatarRef.putFile(uri).addOnCompleteListener {
            if (it.isSuccessful) {
                val localFile = File.createTempFile("avatar", "jgp")
                avatarRef.getFile(localFile).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        presenter.avatarUpdated(Uri.fromFile(localFile))
                    }
                }
            } else {
                presenter.avatarUpdated(null)
            }
        }
    }
}