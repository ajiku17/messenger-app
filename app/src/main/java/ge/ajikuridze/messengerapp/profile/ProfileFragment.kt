package ge.ajikuridze.messengerapp.profile

import android.os.Bundle
import android.util.Log
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
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.graphics.BitmapFactory
import android.content.ContentResolver


import android.app.Activity.RESULT_OK
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import java.io.InputStream


class ProfileFragment() : Fragment(), IProfileView {

    private var presenter: IProfilePresenter = ProfilePresenter(this)
    private lateinit var profilePicture: ImageView
    private lateinit var nameField: TextView
    private lateinit var professionField: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var avatarProgressBar: ProgressBar
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
        progressBar = view.findViewById(R.id.profile_progress_bar)
        avatarProgressBar = view.findViewById(R.id.profile_avatar_progress_bar)

        avatarProgressBar.visibility = View.VISIBLE
        profilePicture.visibility = View.INVISIBLE
        initListeners()

        presenter.fetchCurrentUser()
    }

    override fun userFetched(acc: Account?) {
        if (acc != null) {
            nameField.text = acc.name
            professionField.text = acc.profession
            progressBar.visibility = View.INVISIBLE
            presenter.fetchAvatar()
        }
    }

    override fun avatarFetched(localUri: Uri?) {
        if (localUri != null) {
            setAvatarFromLocal(localUri)
        }
        avatarProgressBar.visibility = View.INVISIBLE
        profilePicture.visibility = View.VISIBLE
    }

    override fun accountUpdated(result: Boolean) {
        Log.i("profile", "updated woooo")
        progressBar.visibility = View.INVISIBLE
    }

    override fun avatarUpdated(localUri: Uri?) {
        if (localUri != null) {
            setAvatarFromLocal(localUri)
            avatarProgressBar.visibility = View.INVISIBLE
            profilePicture.visibility = View.VISIBLE
        }
    }

    private fun setAvatarFromLocal(localUri: Uri) {
        Glide.with(this).load(localUri).circleCrop().into(profilePicture)
    }

    val getContent: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.i("profile", "activity result got")
        if (it.resultCode == RESULT_OK && null != it.data) {
            val imagePath: Uri = it.data!!.getData() ?: return@registerForActivityResult

            presenter.updateAccountAvatar(imagePath)
        }
    }

    private fun initListeners() {
        signOut.setOnClickListener {
            presenter.signOut()
            SignInActivity.start(this.requireContext())
        }
        updateButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            presenter.updateAccount(nameField.text.toString(), professionField.text.toString())
        }
        profilePicture.setOnClickListener {
            avatarProgressBar.visibility = View.VISIBLE
            profilePicture.visibility = View.INVISIBLE
            val i = Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            getContent.launch(i)
        }
    }

}