package ge.ajikuridze.messengerapp.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Message

class ChatActivity : AppCompatActivity(), IChatView {

    private lateinit var messageList: RecyclerView
    private lateinit var listAdapter: MessageListAdapter

    private var presenter: IChatPresenter = ChatPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageList = findViewById(R.id.message_list)
        listAdapter = MessageListAdapter(arrayListOf())
        messageList.adapter = listAdapter

        presenter.fetchMessages()
    }

    override fun messagesFetched(messages: ArrayList<Message>) {
        listAdapter.updateData(messages)
    }

}