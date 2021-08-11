package ge.ajikuridze.messengerapp.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.FirebaseApp
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.Message

class ChatActivity : AppCompatActivity(), IChatView {

    private lateinit var messageList: RecyclerView
    private lateinit var listAdapter: MessageListAdapter
    private lateinit var nameLabel: TextView
    private lateinit var professionLabel: TextView
    private lateinit var toolbar: MaterialToolbar

    private lateinit var messageField: EditText
    private lateinit var sendButton: ImageButton

    private lateinit var otherAcc: Account
    private lateinit var conv: Conversation

    private lateinit var presenter: IChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        presenter = ChatPresenter(this)

        val otherAccountId = intent.getStringExtra(CHAT_WITH_EXTRA)

        messageList = findViewById(R.id.message_list)
        listAdapter = MessageListAdapter(arrayListOf())
        messageList.adapter = listAdapter

        toolbar = findViewById(R.id.chat_toolbar)
        nameLabel = findViewById(R.id.chat_name_label)
        professionLabel = findViewById(R.id.chat_profession_label)

        messageField = findViewById(R.id.message_field)
        sendButton = findViewById(R.id.chat_send_button)

        addListeners()

        if (otherAccountId != null) {
            presenter.fetchAccountById(otherAccountId)
        }
    }

    fun addListeners() {
        toolbar.setNavigationOnClickListener {
            finish()
        }

        sendButton.setOnClickListener {
            val messageText = messageField.text.toString()
            messageField.setText("")
            if (messageText.isEmpty()) {
                return@setOnClickListener
            }

            val message = Message(
                convUid = conv.id!!,
                text = messageText
            )
            presenter.sendMessage(message)
        }
    }

    override fun accountFetched(acc: Account?) {
        if (acc != null) {
            otherAcc = acc
            presenter.fetchConversationWith(acc.id!!)
        }
    }

    override fun conversationCreated(convId: String, accId: String) {
        presenter.fetchConversationWith(accId)
    }

    override fun onNewMessages(newList: ArrayList<Message>) {
        listAdapter.updateData(newList)
    }

    override fun messagesFetched(messages: ArrayList<Message>) {
        listAdapter.updateData(messages)
    }

    override fun conversationFetched(conv: Conversation?) {
        if (conv == null) {
            presenter.createNewConversationWith(otherAcc)
        } else {
            // all good
            Log.d("chat activity","conversation fetched with messages${conv.messages?.size}")
            this.conv = conv
            if (conv.messages != null) {
                listAdapter.updateData(ArrayList(conv.messages!!.values))
            }
        }
    }

    companion object {
        const val CHAT_WITH_EXTRA = "ge.ajikuridze.messengerapp.CHAT_WITH_EXTRA"

        fun start(context: Context, otherAccountId: String) {
            context.startActivity(Intent(context, ChatActivity::class.java).apply {
                putExtra(CHAT_WITH_EXTRA, otherAccountId)
            })
        }
    }

}