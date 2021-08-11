package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.Message

interface IChatView {
    fun messagesFetched(messages: ArrayList<Message>)
    fun conversationFetched(conv: Conversation?)
    fun accountFetched(acc: Account?)
    fun conversationCreated(convId: String, accId: String)
    fun onNewMessages(newList: ArrayList<Message>)
}