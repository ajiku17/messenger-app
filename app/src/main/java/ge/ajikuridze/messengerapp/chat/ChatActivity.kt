package ge.ajikuridze.messengerapp.chat

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.FirebaseApp
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.Message
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.File
import java.io.InputStream


class ChatActivity : AppCompatActivity(), IChatView {

    private lateinit var messageList: RecyclerView
    private lateinit var listAdapter: MessageListAdapter
    private lateinit var nameLabel: TextView
    private lateinit var professionLabel: TextView
    private lateinit var avatarImage: ImageView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var progressBar: ProgressBar

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
        avatarImage = findViewById(R.id.chat_avatar)
        progressBar = findViewById(R.id.progress_bar)

        messageField = findViewById(R.id.message_field)
        sendButton = findViewById(R.id.chat_send_button)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        messageList.layoutManager = linearLayoutManager

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
            nameLabel.text = acc.name
            professionLabel.text = acc.profession
            presenter.fetchConversationWith(acc.id!!)
            presenter.fetchAvatarOf(acc.id!!)
        }
    }

    override fun avatarFetched(file: File?, id: String) {
        if (file != null) {
            val imageStream: InputStream =
                this.contentResolver?.openInputStream(Uri.fromFile(file)) ?: return
            val image: Bitmap = BitmapFactory.decodeStream(imageStream)
            avatarImage.setImageBitmap(image)
        }
        
        //disable loader
    }

    override fun conversationCreated(convId: String, accId: String) {
        presenter.fetchConversationWith(accId)
    }

    override fun onNewMessages(newList: ArrayList<Message>) {
        listAdapter.updateData(ArrayList(newList.sortedByDescending { it.timestamp }))
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
                listAdapter.updateData(ArrayList(conv.messages!!.values.sortedByDescending { it.timestamp }))
            }
            progressBar.visibility = View.INVISIBLE
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