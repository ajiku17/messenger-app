package ge.ajikuridze.messengerapp.conversations

import ge.ajikuridze.messengerapp.models.ConversationPreview

interface IConversationsView {
    fun conversationsFetched(data: ArrayList<ConversationPreview>)
}