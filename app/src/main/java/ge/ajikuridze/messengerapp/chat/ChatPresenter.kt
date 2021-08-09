package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Message

class ChatPresenter(var view: IChatView): IChatPresenter {
    private var interactor: IChatInteractor = ChatInteractor(this)


    override fun fetchMessages() {
        interactor.fetchMessages()
    }

    override fun messagesFetched(messages: ArrayList<Message>) {
        view.messagesFetched(messages)
    }

}