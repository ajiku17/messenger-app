package ge.ajikuridze.messengerapp.chat

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.Message

class ChatInteractor(var presenter: IChatPresenter): IChatInteractor {

    private val conversations = Firebase.database.getReference("conversations")
    private val accounts = Firebase.database.getReference("accounts")
    private val auth = Firebase.auth

    private fun addMessagesValueListener(convUid: String) {
        conversations.child(convUid).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = snapshot.getValue<MutableMap<String, Message>>()
                    messages?.apply {
                        forEach { (_, message) ->
                            message.convUid = convUid
                            message.received = message.sender != auth.currentUser?.uid
                        }
                        presenter.onNewMessages(messages.values.sortedBy {
                            it.timestamp
                        } as ArrayList<Message>)
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            }
        )
    }

    override fun fetchAccountById(id: String) {
        accounts.child(id).get()
            .addOnSuccessListener { snapshot ->
                val acc = snapshot.getValue<Account>()
                acc?.id = id
                presenter.accountFetched(acc)
            }
            .addOnFailureListener {
                presenter.accountFetched(null)
            }
    }

    override fun fetchConversationById(id: String) {
        conversations.get().addOnSuccessListener { snapshot ->
            val conv = snapshot.getValue<MutableMap<String, Conversation>>()
            if (conv != null) {
                if (conv.containsKey(id)) {
                    if (conv[id] != null) {
                        addMessagesValueListener(conv[id]?.uid!!)
                    }
                    presenter.conversationFetched(conv[id])
                }
            }
        }
    }


    override fun createNewConversationWith(otherAccountId: String) {
        val newConvKey = conversations.push().key ?: return

        val accountChanges = hashMapOf<String, Any> (
            "${otherAccountId}/convToUserId/${newConvKey}" to auth.currentUser!!.uid,
            "${auth.currentUser!!.uid}/convToUserId/${newConvKey}" to otherAccountId,
        )

        accounts.updateChildren(accountChanges)
            .addOnSuccessListener {
                presenter.conversationCreated(newConvKey, otherAccountId)
            }
    }

    override fun fetchConversationWith(accId: String) {
        accounts.child(auth.currentUser?.uid!!).get()
            .addOnSuccessListener { snapshot ->
                val acc = snapshot.getValue<Account>()
                if (acc != null) {
                    var chatId: String? = null
                    if (acc.convToUserId?.entries != null) {
                        for ((key, value) in acc.convToUserId?.entries!!) {
                            if (value == accId) {
                                chatId = key
                                break
                            }
                        }
                    }
                    if (chatId != null) {
                        fetchConversationById(chatId)
                    } else {
                        presenter.conversationFetched(null)
                    }
                }
            }
            .addOnFailureListener {
                presenter.conversationFetched(null)
            }
    }

    override fun sendMessage(message: Message) {
        message.sender = auth.currentUser?.uid
        val convUid = message.convUid!!

        conversations.child(convUid).child("lastMessage").setValue(message)
        conversations.child(convUid).child("messages").push().setValue(message)
    }

}