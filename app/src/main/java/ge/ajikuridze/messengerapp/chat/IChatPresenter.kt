package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.Message
import java.io.File

interface IChatPresenter {
    fun fetchAccountById(id: String)
    fun accountFetched(acc: Account?)

    fun fetchMessagesWith(otherAccountId: String)
    fun messagesFetched(messages: ArrayList<Message>)

    fun fetchConversationWith(accId: String)
    fun conversationFetched(conv: Conversation?)

    fun createNewConversationWith(acc: Account)
    fun conversationCreated(convId: String, accId: String)

    fun sendMessage(message: Message)
    fun onNewMessages(newList: ArrayList<Message>)
    fun fetchAvatarOf(id: String)
    fun avatarFetched(file: File?, id: String)
}