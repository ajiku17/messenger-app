package ge.ajikuridze.messengerapp.conversations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.ConversationPreview

class ConversationsFragment() : Fragment(), IConversationsView {

    private lateinit var conversationsList: RecyclerView
    private lateinit var listAdapter: ConversationsListAdapter

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

        listAdapter = ConversationsListAdapter(arrayListOf())
        conversationsList.adapter = listAdapter

        presenter.fetchConversations()
    }

    override fun conversationsFetched(data: ArrayList<ConversationPreview>) {
        listAdapter = ConversationsListAdapter(data)
        conversationsList.adapter = listAdapter
    }

    fun updateConversations(data: ArrayList<ConversationPreview>) {
        listAdapter.updateData(data)
    }

}