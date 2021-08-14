package ge.ajikuridze.messengerapp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import ge.ajikuridze.messengerapp.MainActivity
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.databinding.ActivitySignInBinding
import ge.ajikuridze.messengerapp.models.Account

class SignInActivity : AppCompatActivity(), ILoginView {

    private lateinit var presenter: ILoginPresenter
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = AccountPresenter(this)

        disableLoader()
        initListeners()
        if (presenter.isUserLoggedIn()) {
            MainActivity.start(this)
        }
    }

    fun initListeners() {
        binding.signUp.setOnClickListener {
            SignUpActivity.start(this)
        }

        binding.signIn.setOnClickListener {
            val name = binding.nicknameField.text.toString()
            val pass = binding.passwordField.text.toString()

            enableLoader()
            presenter.loginUser(name, pass)
        }
    }

    override fun currentUserFetched(account: Account?) {}

    override fun onLoginResult(result: Boolean) {
        disableLoader()
        if (result) {
            MainActivity.start(this)
        } else {
            showError("Could not sign in user")
        }
    }

    override fun userRegistered(accunt: Account?, result: Boolean) {}

    fun showError(msg :String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun enableLoader() {
        binding.signInProgressBar.visibility = View.VISIBLE
    }

    fun disableLoader() {
        binding.signInProgressBar.visibility = View.INVISIBLE
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SignInActivity::class.java))
        }

    }

}