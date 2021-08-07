package ge.ajikuridze.messengerapp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ge.ajikuridze.messengerapp.MainActivity
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), ILoginView {

    private var presenter: ILoginPresenter = AccountPresenter(this)
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    fun initListeners() {
        binding.signUp.setOnClickListener {
            val name = binding.nicknameField.text.toString()
            val pass = binding.passwordField.text.toString()
            val profession = binding.professionField.text.toString()

            if (presenter.registerUser(name, pass, profession)) {
                MainActivity.start(this)
            } else {
                showError("Could not register user")
            }
        }
    }

    fun showError(msg :String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
    }
}