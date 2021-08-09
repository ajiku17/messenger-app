package ge.ajikuridze.messengerapp.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Message

class MessageListAdapter(private var data :ArrayList<Message>): RecyclerView.Adapter<MessageListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageListItemViewHolder {
        // TODO
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item_sent, parent, false)
        return MessageListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageListItemViewHolder, position: Int) {
        val preview = data[position]

        // init holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: ArrayList<Message>) {
        this.data = data
        notifyDataSetChanged()
    }

}


class MessageListItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
//    var avatar: ImageView = view.findViewById(R.id.conversations_item_avatar)
//    var name: TextView = view.findViewById(R.id.nameLabel)
//    var lastMessage: TextView = view.findViewById(R.id.lastMessageLabel)
//    var lastMessageDate: TextView = view.findViewById(R.id.lastMessageTimeLabel)
}