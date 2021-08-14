package ge.ajikuridze.messengerapp.conversations

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.content.contentValuesOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import ge.ajikuridze.messengerapp.avatarfetcher.AvatarFetcher
import ge.ajikuridze.messengerapp.avatarfetcher.AvatarListener
import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.ConversationPreview
import java.io.File
import java.io.InputStream

class ConversationsInteractor(var presenter: IConversationsPresenter): IConversationsInteractor, AvatarListener {

    private var auth: FirebaseAuth = Firebase.auth
    private var accounts = Firebase.database.getReference("accounts")
    private var conversationsdb = Firebase.database.getReference("conversations")
    private var avatarFetcher: AvatarFetcher = AvatarFetcher(this)

    override fun fetchConversations(filterStr: String?) {
        accounts.child(auth.currentUser?.uid!!).get().addOnSuccessListener { userSnapshot ->
            val currentUser = userSnapshot.getValue<Account>()

            if (currentUser?.convToUserId != null) {
                val currentConversations = currentUser.convToUserId!!
                conversationsdb.get().addOnSuccessListener { convSnap ->
                    val convs = convSnap.getValue<Map<String, Conversation>>()?.filter { (convId, _) ->
                            currentConversations.containsKey(convId)
                        }

                    accounts.get().addOnSuccessListener { accountsSnap ->
                        val allAccounts = accountsSnap.getValue<Map<String, Account>>()
                        val conversationPreviews = mutableListOf<ConversationPreview>()

                        convs?.onEach { (convId, conv) ->
                            val otherAccount = allAccounts!!.get(currentConversations[convId]!!)
                            if (otherAccount != null && currentConversations[convId] != null) {
                                otherAccount.id = currentConversations[convId]
                                if (filterStr == null || otherAccount.name!!.contains(filterStr)) {
                                    val preview =
                                        conv.lastMessage?.let {
                                            ConversationPreview(otherAcc = otherAccount, message = it)
                                        }
                                    if (preview != null) {
                                        conversationPreviews.add(conversationPreviews.size, preview)
                                    }
                                }
                            }
                        }

                        presenter.conversationsFetched(ArrayList(conversationPreviews.sortedByDescending {
                            it.message.timestamp
                        }))
                    }


                    } .addOnFailureListener {
                        presenter.conversationsFetched(arrayListOf())
                        Log.d("conversations", "failed to fetch ")
                    }
            } else {
                presenter.conversationsFetched(arrayListOf())
            }
        }.addOnFailureListener {
            Log.d("conversations", "fetch error")
            presenter.conversationsFetched(arrayListOf())
        }
    }

    override fun fetchAvatarOf(id: String) {
        avatarFetcher.fetchAvatarById(id)
    }

    override fun avatarFetched(file: File?, id: String) {
        presenter.avatarFetched(file, id)
    }

}