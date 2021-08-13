package ge.ajikuridze.messengerapp.conversations

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.ajikuridze.messengerapp.R
import ge.ajikuridze.messengerapp.Utils
import ge.ajikuridze.messengerapp.models.ConversationPreview
import java.io.InputStream

class ConversationsListAdapter(val listener: ConversationItemListener,
                               private var data :ArrayList<ConversationPreview>):
    RecyclerView.Adapter<ConversationsListItemViewHolder>() {

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
        holder.lastMessageDate.text = Utils.formatTime(conv.message.timestamp!!)

        if (conv.avatarBitmap != null) {
            holder.avatar.setImageBitmap(conv.avatarBitmap)
        }

        holder.itemView.setOnClickListener {
            listener.conversationItemClicked(conv)
        }

        listener.viewBinded(position, conv)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData :ArrayList<ConversationPreview>) {
        data = newData
        notifyDataSetChanged()
    }

    fun updateItem(conv: ConversationPreview, position: Int) {
        data[position] = conv
        notifyItemChanged(position)
    }

}


class ConversationsListItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var avatar: ImageView = view.findViewById(R.id.conversations_item_avatar)
    var name: TextView = view.findViewById(R.id.nameLabel)
    var lastMessage: TextView = view.findViewById(R.id.lastMessageLabel)
    var lastMessageDate: TextView = view.findViewById(R.id.lastMessageTimeLabel)
}