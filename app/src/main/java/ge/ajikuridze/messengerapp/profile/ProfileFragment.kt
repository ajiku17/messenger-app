package ge.ajikuridze.messengerapp.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.conversations.ConversationsListAdapter
import ge.ajikuridze.messengerapp.models.Account

class ProfileFragment : Fragment(), IProfileView {

    private var presenter: IProfilePresenter = ProfilePresenter(this)
    private lateinit var profilePicture: ImageView
    private lateinit var nameField: TextView
    private lateinit var professionField: TextView

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

        presenter.fetchCurrentUser()
    }

    override fun userFetched(acc: Account) {
        TODO("Not yet implemented")
    }

}