package ge.ajikuridze.messengerapp.chat

import ge.ajikuridze.messengerapp.models.Message

interface IChatView {
    fun messagesFetched(messages: ArrayList<Message>)
}