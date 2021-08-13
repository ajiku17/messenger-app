package ge.ajikuridze.messengerapp.search

import ge.ajikuridze.messengerapp.models.Account
import ge.ajikuridze.messengerapp.models.ConversationPreview

interface SearchListItemListener {
    fun accountClicked(acc: Account)
    fun viewBinded(position: Int, acc: Account)

}