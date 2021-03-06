package ge.ajikuridze.messengerapp.conversations

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.chat.ChatActivity
import ge.ajikuridze.messengerapp.models.ConversationPreview
import java.io.File

class ConversationsFragment() : Fragment(), IConversationsView, ConversationItemListener {

    private lateinit var conversationsList: RecyclerView
    private lateinit var listAdapter: ConversationsListAdapter
    private lateinit var progressBar: ProgressBar
    private var data :ArrayList<ConversationPreview> = arrayListOf()
    private lateinit var searchField: EditText

    private var presenter: IConversationsPresenter = ConversationsPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conversationsList = view.findViewById(R.id.conversations)
        searchField = view.findViewById(R.id.conversation_search)
        progressBar = view.findViewById(R.id.conversations_progress_bar)

        listAdapter = ConversationsListAdapter(requireContext(), this, arrayListOf())
        conversationsList.adapter = listAdapter

        searchField.addTextChangedListener {
            presenter.filterConversations(it.toString())
        }
        presenter.fetchConversations()
    }

    override fun onResume() {
        super.onResume()

        presenter.fetchConversations()
    }

    override fun conversationsFetched(data: ArrayList<ConversationPreview>) {
        this.data = data
        updateConversations(this.data)
        progressBar.visibility = View.INVISIBLE
    }

    fun updateConversations(data: ArrayList<ConversationPreview>) {
        this.data = data
        listAdapter.updateData(this.data)
        progressBar.visibility = View.INVISIBLE
    }

    override fun conversationItemClicked(preview: ConversationPreview) {
        if (context != null && preview.otherAcc.id != null) {
            ChatActivity.start(requireContext(), preview.otherAcc.id!!)
        }
    }

    override fun viewBinded(position: Int, conv: ConversationPreview) {
        if (conv.avatarUri == null) {
            presenter.fetchAvatarOf(conv.otherAcc.id!!)
        }
    }

    override fun avatarFetched(file: File?, id: String) {
        val localUri: Uri
        if (file != null) {
            localUri = Uri.fromFile(file)
        } else {
            localUri = Utils.getUriToDrawable(requireContext(), R.drawable.avatar_image_placeholder)
        }

        for (i in data.indices) {
            if (data[i].otherAcc.id!! == id) {
                data[i].avatarUri = localUri
                listAdapter.updateItem(data[i], i)
            }
        }
    }

}