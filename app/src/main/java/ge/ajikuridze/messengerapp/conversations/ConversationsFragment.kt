package ge.ajikuridze.messengerapp.conversations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.chat.ChatActivity
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.ConversationPreview

class ConversationsFragment() : Fragment(), IConversationsView, ConversationItemClickListener {

    private lateinit var conversationsList: RecyclerView
    private lateinit var listAdapter: ConversationsListAdapter
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

        conversationsList = view.findViewById(R.id.converations)
        searchField = view.findViewById(R.id.conversation_search)

        listAdapter = ConversationsListAdapter(this, arrayListOf())
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
        updateConversations(data)
    }

    fun updateConversations(data: ArrayList<ConversationPreview>) {
        listAdapter.updateData(data)
    }

    override fun conversationItemClicked(preview: ConversationPreview) {
        if (context != null && preview.otherAcc.id != null) {
            ChatActivity.start(requireContext(), preview.otherAcc.id!!)
        }
    }

}