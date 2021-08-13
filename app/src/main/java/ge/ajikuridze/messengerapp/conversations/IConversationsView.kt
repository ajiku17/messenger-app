package ge.ajikuridze.messengerapp.conversations

import android.graphics.Bitmap
import ge.ajikuridze.messengerapp.models.ConversationPreview
import java.io.File

interface IConversationsView {
    fun conversationsFetched(data: ArrayList<ConversationPreview>)
    fun avatarFetched(file: File?, id: String)
}