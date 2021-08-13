package ge.ajikuridze.messengerapp.conversations

import android.graphics.Bitmap
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.ConversationPreview
import java.io.File

interface IConversationsPresenter {
    fun fetchConversations()
    fun conversationsFetched(data: ArrayList<ConversationPreview>)
    fun filterConversations(filterStr: String)
    fun fetchAvatarOf(id: String)
    fun avatarFetched(file: File?, id: String)
}