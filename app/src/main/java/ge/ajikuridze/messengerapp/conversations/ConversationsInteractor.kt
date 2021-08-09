package ge.ajikuridze.messengerapp.conversations

class ConversationsInteractor(var presenter: IConversationsPresenter): IConversationsInteractor {



    override fun fetchConversations() {
        presenter.conversationsFetched(arrayListOf())
    }
}