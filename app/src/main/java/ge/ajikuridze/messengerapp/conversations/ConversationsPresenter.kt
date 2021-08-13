package ge.ajikuridze.messengerapp.conversations

import android.graphics.Bitmap
import ge.ajikuridze.messengerapp.models.ConversationPreview
import java.io.File

class ConversationsPresenter(var view: IConversationsView): IConversationsPresenter {

    private val interactor: IConversationsInteractor = ConversationsInteractor(this)

    override fun fetchConversations() {
        interactor.fetchConversations(null)
    }

    override fun conversationsFetched(data: ArrayList<ConversationPreview>) {
        view.conversationsFetched(data)
    }

    override fun filterConversations(filterStr: String) {
        interactor.fetchConversations(filterStr)
    }

    override fun fetchAvatarOf(id: String) {
        interactor.fetchAvatarOf(id)
    }

    override fun avatarFetched(file: File?, id: String) {
        view.avatarFetched(file, id)
    }


}