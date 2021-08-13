package ge.ajikuridze.messengerapp.conversations

import ge.ajikuridze.messengerapp.models.ConversationPreview

interface ConversationItemListener {
    fun conversationItemClicked(preview: ConversationPreview)
    fun viewBinded(position: Int, conv: ConversationPreview)
}