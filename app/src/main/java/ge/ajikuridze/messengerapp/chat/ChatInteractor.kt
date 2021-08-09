package ge.ajikuridze.messengerapp.chat

class ChatInteractor(var presenter: IChatPresenter): IChatInteractor {


    override fun fetchMessages() {
        presenter.messagesFetched(arrayListOf())
    }

}