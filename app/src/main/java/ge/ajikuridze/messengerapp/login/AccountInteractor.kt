package ge.ajikuridze.messengerapp.login

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import ge.ajikuridze.messengerapp.models.Account
import java.util.concurrent.Executor

class AccountInteractor(var presenter: ILoginPresenter): ILoginInteractor, Executor {

    private val auth: FirebaseAuth = Firebase.auth
    private val accounts = FirebaseDatabase.getInstance().getReference("accounts")

    override fun currentUserExists(): Boolean {
        return auth.currentUser != null
    }

    override fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                presenter.onLoginResult(task.isSuccessful)
            }
    }

    override fun registerUser(email: String, account: Account, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    saveAccount(account)
                } else {
                    presenter.userRegistered(account, false)
                }
            }
            .addOnFailureListener {
                Log.e("register", it.toString())
            }
    }

    private fun saveAccount(account: Account) {
        auth.currentUser?.uid?.let { uid ->
            accounts.child(uid).setValue(account).addOnSuccessListener {
                presenter.userRegistered(account, true)
            }.addOnFailureListener {
                presenter.userRegistered(account, false)
            }
        }
    }

    override fun execute(command: Runnable?) {
        command?.run()
    }
}