package ge.ajikuridze.messengerapp.conversations

import ge.ajikuridze.messengerapp.models.ConversationPreview

interface ConversationItemClickListener {
    fun conversationItemClicked(preview: ConversationPreview)
}