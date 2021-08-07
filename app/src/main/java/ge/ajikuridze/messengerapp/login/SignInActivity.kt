package ge.ajikuridze.messengerapp.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ge.ajikuridze.messengerapp.MainActivity
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), ILoginView {

    private var presenter: ILoginPresenter = AccountPresenter(this)
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

            if (presenter.loginUser(name, pass)) {
                MainActivity.start(this)
            }
        }
    }



}