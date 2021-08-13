package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Message

interface IChatInteractor {
    fun fetchAccountById(id: String)
    fun fetchConversationById(id: String)
    fun createNewConversationWith(otherAccountId: String)
    fun fetchConversationWith(accId: String)
    fun sendMessage(message: Message)
    fun fetchAvatarOf(id: String)
}