package ge.ajikuridze.messengerapp.conversations

import ge.ajikuridze.messengerapp.models.ConversationPreview

interface IConversationsPresenter {
    fun fetchConversations()
    fun conversationsFetched(data: ArrayList<ConversationPreview>)
}