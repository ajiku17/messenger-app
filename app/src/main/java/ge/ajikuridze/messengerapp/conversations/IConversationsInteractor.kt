package ge.ajikuridze.messengerapp.conversations

interface IConversationsInteractor {
    fun fetchConversations(filterStr: String?)
    fun fetchAvatarOf(id: String)
}