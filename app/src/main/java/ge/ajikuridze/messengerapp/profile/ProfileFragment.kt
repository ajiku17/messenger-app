package ge.ajikuridze.messengerapp.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.conversations.ConversationsListAdapter
import ge.ajikuridze.messengerapp.login.SignInActivity
import ge.ajikuridze.messengerapp.models.Account

class ProfileFragment : Fragment(), IProfileView {

    private var presenter: IProfilePresenter = ProfilePresenter(this)
    private lateinit var profilePicture: ImageView
    private lateinit var nameField: TextView
    private lateinit var professionField: TextView
    private lateinit var signOut: Button
    private lateinit var updateButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profilePicture = view.findViewById(R.id.profile_pic)
        nameField = view.findViewById(R.id.nickname_field)
        professionField = view.findViewById(R.id.profession_field)
        signOut = view.findViewById(R.id.sign_out)
        updateButton = view.findViewById(R.id.update_button)

        initListeners()

        presenter.fetchCurrentUser()
    }

    override fun userFetched(acc: Account?) {
        if (acc != null) {
            nameField.text = acc.name
            professionField.text = acc.profession
        }
    }

    override fun accountUpdated(result: Boolean) {

    }

    private fun initListeners() {
        signOut.setOnClickListener {
            presenter.signOut()
            SignInActivity.start(this.requireContext())
        }
        updateButton.setOnClickListener {
            presenter.updateAccount(nameField.text.toString(), professionField.text.toString())
        }
    }

}