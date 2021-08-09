package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Message

interface IChatPresenter {
    fun fetchMessages()
    fun messagesFetched(messages: ArrayList<Message>)
}