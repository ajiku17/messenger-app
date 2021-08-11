package ge.ajikuridze.messengerapp.conversations

import ge.ajikuridze.messengerapp.models.ConversationPreview

class ConversationsPresenter(var view: IConversationsView): IConversationsPresenter {

    private val interactor = ConversationsInteractor(this)

    override fun fetchConversations() {
        interactor.fetchConversations(null)
    }

    override fun conversationsFetched(data: ArrayList<ConversationPreview>) {
        view.conversationsFetched(data)
    }

    override fun filterConversations(filterStr: String) {
        interactor.fetchConversations(filterStr)
    }
}