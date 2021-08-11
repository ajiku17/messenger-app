package ge.ajikuridze.messengerapp.conversations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.models.Conversation
import ge.ajikuridze.messengerapp.models.ConversationPreview

class ConversationsListAdapter(private var data :ArrayList<ConversationPreview>): RecyclerView.Adapter<ConversationsListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConversationsListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coversation_item, parent, false)
        return ConversationsListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConversationsListItemViewHolder, position: Int) {
        val conv = data[position]

        // init holder
        holder.name.text = conv.otherAcc.name
        holder.lastMessage.text = conv.message.text
        holder.lastMessageDate.text = conv.message.timestamp.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData :ArrayList<ConversationPreview>) {
        data = newData
        notifyDataSetChanged()
    }

}


class ConversationsListItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var avatar: ImageView = view.findViewById(R.id.conversations_item_avatar)
    var name: TextView = view.findViewById(R.id.nameLabel)
    var lastMessage: TextView = view.findViewById(R.id.lastMessageLabel)
    var lastMessageDate: TextView = view.findViewById(R.id.lastMessageTimeLabel)
}