package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.Message
import java.io.File

class ChatPresenter(var view: IChatView): IChatPresenter {
    private var interactor: IChatInteractor = ChatInteractor(this)

    override fun fetchAccountById(id: String) {
        interactor.fetchAccountById(id)
    }

    override fun accountFetched(acc: Account?) {
        view.accountFetched(acc)
    }

    override fun fetchMessagesWith(otherAccountId: String) {
        interactor.fetchConversationById(otherAccountId)
    }

    override fun messagesFetched(messages: ArrayList<Message>) {
        view.messagesFetched(messages)
    }

    override fun fetchConversationWith(accId: String) {
        interactor.fetchConversationWith(accId)
    }

    override fun conversationFetched(conv: Conversation?) {
        view.conversationFetched(conv)
    }

    override fun createNewConversationWith(acc: Account) {
        interactor.createNewConversationWith(acc.id!!)
    }

    override fun conversationCreated(convId: String, accId: String) {
        view.conversationCreated(convId, accId)
    }

    override fun sendMessage(message: Message) {
        interactor.sendMessage(message)
    }

    override fun onNewMessages(newList: ArrayList<Message>) {
        view.onNewMessages(newList)
    }

    override fun fetchAvatarOf(id: String) {
        interactor.fetchAvatarOf(id)
    }

    override fun avatarFetched(file: File?, id: String) {
        view.avatarFetched(file, id)
    }

}