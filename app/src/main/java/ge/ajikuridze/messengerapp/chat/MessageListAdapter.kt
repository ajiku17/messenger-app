package ge.ajikuridze.messengerapp.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.models.Message

class MessageListAdapter(private var data :ArrayList<Message>): RecyclerView.Adapter<MessageListItemViewHolder>() {

    val SENT     = 1
    val RECEIVED = 2

    override fun getItemViewType(position: Int): Int {
        val message = data[position]
        if (message.sent == true) {
            return SENT
        }
        return RECEIVED
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageListItemViewHolder {
        val view = if (viewType == SENT) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_item_sent, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_item_received, parent, false)
        }

        return MessageListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageListItemViewHolder, position: Int) {
        val message = data[position]

        holder.messageText.text = message.text
        holder.messsageTime.text = message.timestamp?.let { Utils.formatTime(it) }
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
    var messageText: TextView = view.findViewById(R.id.message_view)
    var messsageTime: TextView = view.findViewById(R.id.time_sent_view)
}